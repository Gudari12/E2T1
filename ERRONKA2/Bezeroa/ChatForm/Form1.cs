using System.Net.Sockets;

namespace ChatForm
{
    public partial class Form1 : Form
    {
        private TcpClient client = null;
        private NetworkStream stream = null;
        private StreamReader reader = null;
        private StreamWriter writer = null;

        private int saiakerak = 0;
        private int maxSaiakerak = 3;

        // Configuración del servidor
        private string serverIP = "192.168.208.62";
        private int serverPort = 20000;

        public Form1()
        {
            InitializeComponent();
        }

        private async void button1_Click(object sender, EventArgs e)
        {
            string erabiltzailea = textBox1.Text.Trim();
            string pasahitza = textBox2.Text.Trim();

            if (string.IsNullOrEmpty(erabiltzailea) || string.IsNullOrEmpty(pasahitza))
            {
                MessageBox.Show("Sartu erabiltzailea eta pasahitza", "Errorea",
                    MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            // Deshabilitar botón durante la conexión
            button1.Enabled = false;
            button1.Text = "Konektatzen...";

            try
            {
                // Si es el primer intento, conectar
                if (client == null || !client.Connected)
                {
                    await KonektatuServidorera();
                }

                // Enviar credenciales
                await writer.WriteLineAsync(erabiltzailea);
                await writer.WriteLineAsync(pasahitza);

                // Esperar respuesta
                string erantzuna = await reader.ReadLineAsync();

                if (erantzuna == "LOGINZUZENA")
                {
                    // Éxito - Abrir Form2
                    this.Hide();
                    Form2 form2 = new Form2(erabiltzailea, client, stream, reader, writer);
                    form2.ShowDialog();
                    this.Close();
                }
                else if (erantzuna.StartsWith("LOGINOKERRA"))
                {
                    saiakerak++;
                    string[] partes = erantzuna.Split('-');
                    int falta = int.Parse(partes[1]);

                    if (falta > 0)
                    {
                        MessageBox.Show($"Kredentzial okerrak. Saiakera geratzen dira: {falta}",
                            "Errorea", MessageBoxButtons.OK, MessageBoxIcon.Error);

                        textBox2.Clear();
                        textBox2.Focus();

                        // Cerrar conexión actual y permitir reintentar
                        ItxiKonexioa();
                        button1.Enabled = true;
                        button1.Text = "Hasi saioa";
                    }
                    else
                    {
                        MessageBox.Show("Saiakera kopurua agortu da. Programa itxiko da.",
                            "Errorea", MessageBoxButtons.OK, MessageBoxIcon.Error);
                        this.Close();
                    }
                }
                else if (erantzuna == "TXATBETETA")
                {
                    MessageBox.Show("Txata beteta dago. Saiatu geroago.",
                        "Errorea", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    ItxiKonexioa();
                    button1.Enabled = true;
                    button1.Text = "Hasi saioa";
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Konexio errorea: {ex.Message}", "Errorea",
                    MessageBoxButtons.OK, MessageBoxIcon.Error);
                ItxiKonexioa();
                button1.Enabled = true;
                button1.Text = "Hasi saioa";
            }
        }

        private async Task KonektatuServidorera()
        {
            client = new TcpClient();
            await client.ConnectAsync(serverIP, serverPort);

            stream = client.GetStream();
            reader = new StreamReader(stream);
            writer = new StreamWriter(stream) { AutoFlush = true };

            // Esperar confirmación "OK" del servidor
            string respuesta = await reader.ReadLineAsync();
            if (respuesta != "OK")
            {
                throw new Exception("Erantzun okerra zerbitzaritik");
            }
        }

        private void ItxiKonexioa()
        {
            try { writer?.Close(); } catch { }
            try { reader?.Close(); } catch { }
            try { stream?.Close(); } catch { }
            try { client?.Close(); } catch { }

            writer = null;
            reader = null;
            stream = null;
            client = null;
        }
    }
}