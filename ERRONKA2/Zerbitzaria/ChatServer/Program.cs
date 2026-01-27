using System.Collections.Concurrent;
using System.Net;
using System.Net.Sockets;

namespace ChatZerbitzaria
{
    class ChatServer
    {
        //
        // Klasearen atributuak.
        //

        // Zerbitzaria entzuten egongo den portu-zenbakia eta IP helbidea.
        int port = 20000;
        IPAddress localAddr = IPAddress.Parse("192.168.208.62");

        // Zerbitzariaren socket-a.
        TcpListener server;

        // Onartuko diren bezero kopuru maximoa.
        int bezeroKopuruMax = 15;
        // Konektatuta dauden bezeroen kontagailua.
        int bezeroKopurua = 0;

        // Jokoan erantzun behar den galdera eta bere erantzuna.
        string erab = null;
        string pasahitza = null;
        string[] erabiltzaileak = new string[] { "eneko", "mikel", "ainhoa", "juan", "jose" };
        string[] pasahitzak = new string[] { "123", "456", "789", "juan", "jose" };

        /**
         * Eraikitzailea. 
         */
        public ChatServer()
        {
            // TcpListener objektua sortzen dugu.
            this.server = new TcpListener(this.localAddr, this.port);
        }

        /**
         * Hasierazi zerbitzaria.
         */
        private async Task BezeroSarrera()
        {
            try
            {

                // Sistema Eragileari esaten diogu protu-zenbaki horretara heltzen diren paketeak gure aplikaziora bidali behar dituela.
                this.server.Start();
                Console.WriteLine("Txat zerbitzaria hasita. Bezeroak itxaroten...");
                // Bezeroen identifikazioa kontrolatzeko kontagailua.
                int bezeroZenbakia = 0;
                // Bukle infinitu bat hainbat bezeroen eskaerak erantzun ahal izateko.
                while (true)
                {
                    // Bezero baten konexio eskaera itxaroten gelditzen da.
                    TcpClient socketcliente = await this.server.AcceptTcpClientAsync();
                    // Bezero kopuru maximora ailegatu bagara, informatu bezeroa eta deskonektatu.
                    if (this.bezeroKopurua == this.bezeroKopuruMax)
                    {
                        await PartaidetzaUkatu(socketcliente);
                    }
                    else
                    {
                        this.bezeroKopurua++;
                        bezeroZenbakia++;
                        Console.WriteLine("Bezero berri bat konektatu da: Bezero-" + bezeroZenbakia);

                        // Kudeatu bezeroaren eskaera metodo asinkrono baten, horrela hurrengo bezero baten konexioa kudeatu ahalko da.
                        //      Bueltatzen den Task objektua "_" aldagai baten gordetzen da kasu hauetarako konbenzio bat delako.
                        //      Hau da, ataza asinkrono bat exekutatzerakoan, baina bere emaitza ez dugunean kontrolatu nahi (fire-and-forget).
                        _ = this.Login(socketcliente, bezeroZenbakia);
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Socket edo buffer-a sortzen errorea: {0}", e);
            }
        }

        /**
         * Bezero baten partaidetza ukatu.
         */
        async Task PartaidetzaUkatu(TcpClient socket)
        {
            try
            {
                // Erabili "using" konexioak automatikoki bukatzeko (close deitu gabe).
                using (NetworkStream stream = socket.GetStream())
                using (StreamWriter writer = new StreamWriter(stream))
                {
                    // TXATBETETA mezua bidali bezeroari.
                    await writer.WriteLineAsync("TXATBETETA");
                    await writer.FlushAsync();
                    Console.WriteLine("Bezero bati konexioa ukatuta");
                }
                // Itxi bezeroari konexioa.
                socket.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Komunikazio errorea: {0}", e);
            }
        }

        /**
        * Bezeroaren logina haren kredentialekin egiaztatu.
        */
        async Task Login(TcpClient socket, int bezeroZenbakia)
        {
            try
            {
                // Erabili "using" konexioak automatikoki bukatzeko (close deitu gabe).
                using (NetworkStream stream = socket.GetStream())
                using (StreamWriter writer = new StreamWriter(stream))
                using (StreamReader reader = new StreamReader(stream))
                {
                    writer.AutoFlush = true;

                    // Login orria bidali bezeroari.
                    await writer.WriteLineAsync("LOGIN");
                    // Hartu logineko erabiltzailea eta pasahitza.
                    this.erab = await reader.ReadLineAsync();
                    this.pasahitza = await reader.ReadLineAsync();

                    bool baimena = false;

                    for (int i = 0; i < this.erabiltzaileak.Length; i++)
                    {
                        if (this.erabiltzaileak[i] == this.erab && this.pasahitzak[i] == this.pasahitza)
                        {
                            baimena = true;
                            break;
                        }
                    }

                    if (baimena)
                    {
                        // Login zuzena.
                        Console.WriteLine(bezeroZenbakia + ". partaidea loginean sartu da: " + this.erab);
                        await writer.WriteLineAsync("LOGINZUZENA");
                        await Txat(socket, bezeroZenbakia);
                    }
                    else
                    {
                        // Login okerra.
                        Console.WriteLine(bezeroZenbakia + ". partaidea login okerra egin du: " + this.erab);
                        await writer.WriteLineAsync("LOGINOKERRA");
                        // Itxi bezeroaren konexioa.
                        socket.Close();
                        return;
                    }

                    
                }
                // Itxi bezeroaren konexioa.
                socket.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Komunikazio errorea: {0}", e);
            }
            Console.WriteLine("Bezero-" + bezeroZenbakia + " konexioa itxita.");
        }

        /**
         * Txat.
         */
        async Task Txat(TcpClient socket, int bezeroZenbakia)
        {
            try
            {
                using (NetworkStream stream = socket.GetStream())
                using (StreamWriter writer = new StreamWriter(stream))
                using (StreamReader reader = new StreamReader(stream))
                {
                    writer.AutoFlush = true;
                    string mezua = null;
                    // Mezuak trukatu ahal izateko bukle infinitu bat.
                    while (true)
                    {
                        // Bezeroak mezua bidaltzen du.
                        mezua = await reader.ReadLineAsync();
                        // Bezeroak "IRTEN" mezua bidali badu, saioa amaitu.
                        if (mezua == "IRTEN")
                        {
                            Console.WriteLine(bezeroZenbakia + ". partaidetzat saioa amaitu du.");
                            break;
                        }
                        // Bestela, mezua pantailaratu.
                        Console.WriteLine("Bezero-" + bezeroZenbakia + ": " + mezua);
                        // Mezuari erantzun bat bidali.
                        await writer.WriteLineAsync(this.erab + ": " + mezua);
                    }
                }
            }
        }


        /**
         * Irekitako konexio objektuak itxi.
         */
        private void Itxi()
        {
            try
            {
                this.server.Stop();
                Console.WriteLine("Zerbitzaria bukatuta.");
            }
            catch (Exception e)
            {
                Console.WriteLine("Zerbitzaria ezin izan da gelditU: {0}", e);
            }
        }


        /**
         * Main metodoa, programa hemen hasten da.
         */
        public static async Task<int> Main(string[] args)
        {
            // Guk definitutako klasearen objektua sortu.
            ChatServer zerbitzariAplikazioa = new ChatServer();

            await zerbitzariAplikazioa.BezeroSarrera();
            zerbitzariAplikazioa.Itxi();


            Console.WriteLine("\nSakatu <ENTER> bukatzeko...");
            Console.Read();
            return 0;
        }
    }
}