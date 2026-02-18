using System.Collections.Concurrent;
using System.Net;
using System.Net.Sockets;

namespace ChatZerbitzaria
{
    class ChatServer
    {
        // Configuración
        int port = 20000;
        IPAddress localAddr = IPAddress.Parse("192.168.208.62");
        int bezeroKopuruMax = 15;

        TcpListener server;
        int bezeroKopurua = 0;

        // Lista de clientes conectados para broadcast
        private ConcurrentBag<StreamWriter> bezeroak = new ConcurrentBag<StreamWriter>();

        // Credenciales válidas
        string[] erabiltzaileak = new string[] { "eneko", "mikel", "ainhoa", "juan", "jose" };
        string[] pasahitzak = new string[] { "123", "456", "789", "juan", "jose" };

        public ChatServer()
        {
            this.server = new TcpListener(this.localAddr, this.port);
        }

        private async Task BezeroSarrera()
        {
            try
            {
                this.server.Start();
                Console.WriteLine("=== CHAT ZERBITZARIA ===");
                Console.WriteLine($"Entzuten {this.localAddr}:{this.port}");
                Console.WriteLine($"Gehienez {this.bezeroKopuruMax} bezero");
                Console.WriteLine("========================\n");

                int bezeroZenbakia = 0;

                while (true)
                {
                    TcpClient socketcliente = await this.server.AcceptTcpClientAsync();

                    if (this.bezeroKopurua >= this.bezeroKopuruMax)
                    {
                        await PartaidetzaUkatu(socketcliente);
                    }
                    else
                    {
                        this.bezeroKopurua++;
                        bezeroZenbakia++;
                        Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] Bezero-{bezeroZenbakia} konektatu da (Guztira: {this.bezeroKopurua})");

                        _ = this.KudeatuBezeroa(socketcliente, bezeroZenbakia);
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"Errorea: {e.Message}");
            }
        }

        async Task PartaidetzaUkatu(TcpClient socket)
        {
            try
            {
                using (NetworkStream stream = socket.GetStream())
                using (StreamWriter writer = new StreamWriter(stream))
                {
                    writer.AutoFlush = true;
                    await writer.WriteLineAsync("TXATBETETA");
                    Console.WriteLine("Bezero bati konexioa ukatuta (txata beteta)");
                }
                socket.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine($"Errorea ukatzean: {e.Message}");
            }
        }

        async Task KudeatuBezeroa(TcpClient socket, int bezeroZenbakia)
        {
            string erabiltzaileIzena = "";
            StreamWriter writer = null;

            try
            {
                using (socket)
                using (NetworkStream stream = socket.GetStream())
                using (writer = new StreamWriter(stream))
                using (StreamReader reader = new StreamReader(stream))
                {
                    writer.AutoFlush = true;

                    // FASE 1: LOGIN CON 3 INTENTOS
                    bool loginOndo = false;
                    int saiakerak = 0;
                    int maxSaiakerak = 3;

                    await writer.WriteLineAsync("OK"); // Indicar que estamos listos para login

                    while (!loginOndo && saiakerak < maxSaiakerak)
                    {
                        string erab = await reader.ReadLineAsync();
                        string pass = await reader.ReadLineAsync();

                        // Verificar credenciales
                        for (int i = 0; i < this.erabiltzaileak.Length; i++)
                        {
                            if (this.erabiltzaileak[i] == erab && this.pasahitzak[i] == pass)
                            {
                                loginOndo = true;
                                erabiltzaileIzena = erab;
                                break;
                            }
                        }

                        if (loginOndo)
                        {
                            await writer.WriteLineAsync("LOGINZUZENA");
                            Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] {erabiltzaileIzena} login zuzena (Bezero-{bezeroZenbakia})");
                        }
                        else
                        {
                            saiakerak++;
                            if (saiakerak < maxSaiakerak)
                            {
                                await writer.WriteLineAsync($"LOGINOKERRA-{maxSaiakerak - saiakerak}");
                            }
                            else
                            {
                                await writer.WriteLineAsync("LOGINOKERRA-0");
                                Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] Bezero-{bezeroZenbakia} login okerra x3. Deskonektatzen.");
                                this.bezeroKopurua--;
                                return;
                            }
                        }
                    }

                    if (!loginOndo) return;

                    // Añadir a lista de clientes para broadcast
                    bezeroak.Add(writer);

                    // Notificar a todos que se ha conectado alguien nuevo
                    await BidaliGuztiei($"SISTEMA$#/{erabiltzaileIzena} txat-era batu da!");

                    // FASE 2: CHAT
                    string mezua;
                    while ((mezua = await reader.ReadLineAsync()) != null)
                    {
                        if (mezua == "IRTEN")
                        {
                            Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] {erabiltzaileIzena} irten da (Bezero-{bezeroZenbakia})");
                            await BidaliGuztiei($"SISTEMA$#/{erabiltzaileIzena} irten da.");
                            break;
                        }

                        // Verificar formato: usuario$#/mensaje
                        if (mezua.Contains("$#/"))
                        {
                            string[] zatia = mezua.Split(new[] { "$#/" }, StringSplitOptions.None);
                            if (zatia.Length >= 2)
                            {
                                string bidaltzailea = zatia[0];
                                string testua = string.Join("$#/", zatia.Skip(1));

                                Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] {bidaltzailea}: {testua}");

                                // Reenviar a todos (broadcast)
                                await BidaliGuztiei(mezua);
                            }
                        }
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] Errorea Bezero-{bezeroZenbakia}: {e.Message}");
            }
            finally
            {
                // Limpiar cliente de la lista
                if (writer != null)
                {
                    var lista = bezeroak.ToList();
                    lista.Remove(writer);
                    bezeroak = new ConcurrentBag<StreamWriter>(lista);
                }

                this.bezeroKopurua--;
                Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] Bezero-{bezeroZenbakia} deskonektatu da (Guztira: {this.bezeroKopurua})");
            }
        }

        async Task BidaliGuztiei(string mezua)
        {
            var lista = bezeroak.ToList();
            foreach (var w in lista)
            {
                try
                {
                    await w.WriteLineAsync(mezua);
                }
                catch
                {
                    // Cliente desconectado, se limpiará luego
                }
            }
        }

        private void Itxi()
        {
            try
            {
                this.server.Stop();
                Console.WriteLine("\nZerbitzaria geldituta.");
            }
            catch (Exception e)
            {
                Console.WriteLine($"Errorea gelditzean: {e.Message}");
            }
        }

        public static async Task<int> Main(string[] args)
        {
            ChatServer zerbitzaria = new ChatServer();

            // Capturar Ctrl+C para cerrar limpiamente
            Console.CancelKeyPress += (sender, e) => {
                e.Cancel = true;
                zerbitzaria.Itxi();
                Environment.Exit(0);
            };

            await zerbitzaria.BezeroSarrera();
            return 0;
        }
    }
}