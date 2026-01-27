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
        int port = 13000;
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
                    // JOKOABETETA mezua bidali bezeroari.
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
        * Bezerotik jasotako informazioa irakurri <EOF> jaso arte.
        * Ondoren, bezeroari jasotako mezua letra larriekin bueltatu.
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
                    // Lehenengo gauza bezeroaren partaidetza konfirmatzen dugu "ITXARON-x-y" mezuarekin, non x bezero kopuru Maximoa den eta zenbat bezero falta diren jokoa hasteko.
                    await writer.WriteLineAsync("ITXARON-" + bezeroZenbakia + "-" + (this.bezeroKopuruMax - bezeroZenbakia));

                    // Itxaron partaide danak.
                    while (this.bezeroKopurua < this.bezeroKopuruMax)
                    {
                        await Task.Delay(100);
                    }

                    // Jokoa hasi dela abisatu bezeroei.
                    await writer.WriteLineAsync("JOKOAHASI");
                    // Erakutsi galdera.
                    await writer.WriteLineAsync(this.galdera);

                    string erantzuna = string.Empty;
                    // Irabazlea ez dagoen bitartean, galdetu.
                    while (this.irabazleaId == 0)
                    {
                        erantzuna = await reader.ReadLineAsync();
                        // Irabazle bat egon bada erantzunaren zain geunden bitartean.
                        if (this.irabazleaId != 0)
                        {
                            // Irten while bukletik.
                            break;
                        }
                        else if (erantzuna == this.emaitza)
                        {
                            // Ibazle bat egon da.
                            Console.WriteLine(bezeroZenbakia + ". partaideak IRABAZI du.");
                            // Erantzun zuzena dela bidali bezeroari.
                            this.irabazleaId = bezeroZenbakia;
                            await writer.WriteLineAsync("ZUZENA");
                            break;
                        }
                        else
                        {
                            // Erantzun okerra.
                            Console.WriteLine(bezeroZenbakia + ". partaidea erantzun OKERRA.");
                            // Erantzun okerra dela bidali bezeroari.
                            await writer.WriteLineAsync("OKERRA");
                        }
                    }

                    // Bukletik atera bagara irabazle bat egon delako da.
                    // Ez bada hari honetako bezeroa irabazlea, bidali galdu duela.
                    if (this.irabazleaId != bezeroZenbakia)
                    {
                        Console.WriteLine(bezeroZenbakia + ". partaideak GALDU du.");
                        // Bidali bezeroari galdu duelaren abisua.
                        await writer.WriteLineAsync("GALDUDUZU-" + this.irabazleaId);
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