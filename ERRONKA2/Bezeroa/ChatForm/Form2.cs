using System.Net.Sockets;
using System.Net.Http;
using System.Text.Json;
using ChatForm.Modelos;

namespace ChatForm
{
    public partial class Form2 : Form
    {
        private string erabiltzailea;
        private TcpClient client;
        private NetworkStream stream;
        private StreamReader reader;
        private StreamWriter writer;
        private Thread hartzailea;
        private bool running = true;

        // HttpClient para las APIs (reutilizable)
        private static readonly HttpClient httpClient = new HttpClient();

        // URLs de las APIs
        private string apiStudentUrl = "http://192.168.208.51:8080/api/student";
        private string apiShiftUrl = "http://192.168.208.51:8080/api/shift";

        // Constructor modificado - recibe la conexión establecida
        public Form2(string erabiltzailea, TcpClient client, NetworkStream stream,
                     StreamReader reader, StreamWriter writer)
        {
            InitializeComponent();
            this.erabiltzailea = erabiltzailea;
            this.client = client;
            this.stream = stream;
            this.reader = reader;
            this.writer = writer;
        }

        private async void Form2_Load(object sender, EventArgs e)
        {
            labelUsuario.Text = $"Ongi etorri, {erabiltzailea}!";

            // Configurar columnas de los DataGridView primero
            KonfiguratuDataGridViewak();

            // Cargar datos de las APIs
            await KargatuIkasleak();
            await KargatuTxandak();

            // Iniciar hilo para escuchar mensajes del servidor
            hartzailea = new Thread(new ThreadStart(EskuratuMezuak));
            hartzailea.IsBackground = true;
            hartzailea.Start();

            // Mensaje de bienvenida local
            GehituMezua("SISTEMA", "Txat-era ongi etorri! $#/ komandoarekin banatzen da izena eta mezua.");
        }

        // ========== MÉTODOS NUEVOS PARA LA API ==========

        private void KonfiguratuDataGridViewak()
        {
            // Configurar DataGridView de Ikasleak (Student)
            dataGridViewIkasleak.AutoGenerateColumns = false;
            dataGridViewIkasleak.Columns.Clear();

            dataGridViewIkasleak.Columns.Add(new DataGridViewTextBoxColumn
            {
                DataPropertyName = "id",
                HeaderText = "ID",
                Width = 50
            });
            dataGridViewIkasleak.Columns.Add(new DataGridViewTextBoxColumn
            {
                DataPropertyName = "name",
                HeaderText = "Izena",
                Width = 150
            });
            dataGridViewIkasleak.Columns.Add(new DataGridViewTextBoxColumn
            {
                DataPropertyName = "surname",
                HeaderText = "Abizena",
                Width = 200
            });

            // Configurar DataGridView de Txandak (Shift)
            dataGridViewTxandak.AutoGenerateColumns = false;
            dataGridViewTxandak.Columns.Clear();

            // Para mostrar name y surname del estudiante anidado
            dataGridViewTxandak.Columns.Add(new DataGridViewTextBoxColumn
            {
                DataPropertyName = "IzenaOsoa", // Propiedad calculada
                HeaderText = "Ikaslea",
                Width = 250
            });
            dataGridViewTxandak.Columns.Add(new DataGridViewTextBoxColumn
            {
                DataPropertyName = "type",
                HeaderText = "Txanda Mota",
                Width = 150
            });
        }

        private async Task KargatuIkasleak()
        {
            try
            {
                // Llamada a la API de estudiantes
                HttpResponseMessage response = await httpClient.GetAsync(apiStudentUrl);

                if (response.IsSuccessStatusCode)
                {
                    string json = await response.Content.ReadAsStringAsync();

                    // Deserializar JSON
                    var options = new JsonSerializerOptions
                    {
                        PropertyNameCaseInsensitive = true
                    };
                    List<Student> ikasleak = JsonSerializer.Deserialize<List<Student>>(json, options);

                    // Asignar al DataGridView (en hilo de UI)
                    this.Invoke(new Action(() =>
                    {
                        dataGridViewIkasleak.DataSource = ikasleak;
                    }));
                }
                else
                {
                    MessageBox.Show($"Errorea ikasleak kargatzean: {response.StatusCode}", "API Errorea");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Errorea ikasleak kargatzean: {ex.Message}", "Errorea");
            }
        }

        private async Task KargatuTxandak()
        {
            try
            {
                // Llamada a la API de shifts
                HttpResponseMessage response = await httpClient.GetAsync(apiShiftUrl);

                if (response.IsSuccessStatusCode)
                {
                    string json = await response.Content.ReadAsStringAsync();

                    // Deserializar JSON
                    var options = new JsonSerializerOptions
                    {
                        PropertyNameCaseInsensitive = true
                    };
                    List<Shift> txandak = JsonSerializer.Deserialize<List<Shift>>(json, options);

                    // Crear lista con datos planos para mostrar
                    var txandakView = txandak.Select(t => new
                    {
                        id = t.id,
                        IzenaOsoa = $"{t.students?.name} {t.students?.surname}",
                        type = t.type
                    }).ToList();

                    // Asignar al DataGridView (en hilo de UI)
                    this.Invoke(new Action(() =>
                    {
                        dataGridViewTxandak.DataSource = txandakView;
                    }));
                }
                else
                {
                    MessageBox.Show($"Errorea txandak kargatzean: {response.StatusCode}", "API Errorea");
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Errorea txandak kargatzean: {ex.Message}", "Errorea");
            }
        }

        // ========== FIN MÉTODOS NUEVOS ==========

        // Hilo que escucha mensajes del servidor continuamente
        private void EskuratuMezuak()
        {
            try
            {
                while (running && client != null && client.Connected)
                {
                    string mezua = reader.ReadLine();
                    if (mezua == null) break; // Servidor cerró conexión

                    // Procesar en el hilo de la UI
                    this.Invoke(new Action(() => ProzesatuMezua(mezua)));
                }
            }
            catch (Exception)
            {
                // Error o cierre
                if (running)
                {
                    this.Invoke(new Action(() =>
                    {
                        GehituMezua("SISTEMA", "Konexioa galdu da.");
                        buttonEnviar.Enabled = false;
                    }));
                }
            }
        }

        private void ProzesatuMezua(string mezua)
        {
            // Formato: usuario$#/mensaje
            if (mezua.Contains("$#/"))
            {
                string[] zatia = mezua.Split(new[] { "$#/" }, 2, StringSplitOptions.None);
                string nor = zatia[0];
                string testua = zatia[1];

                GehituMezua(nor, testua);
            }
            else
            {
                // Mensaje del sistema sin formato especial
                GehituMezua("SISTEMA", mezua);
            }
        }

        private void GehituMezua(string nor, string testua)
        {
            // Crear panel para el mensaje
            Panel mezuPanel = new Panel();
            mezuPanel.Dock = DockStyle.Top;
            mezuPanel.AutoSize = true;
            mezuPanel.MinimumSize = new Size(0, 30);
            mezuPanel.Padding = new Padding(5);

            // Color diferente si es el usuario actual o sistema
            if (nor == erabiltzailea)
            {
                mezuPanel.BackColor = Color.FromArgb(200, 230, 255); // Azul claro (mis mensajes)
            }
            else if (nor == "SISTEMA")
            {
                mezuPanel.BackColor = Color.FromArgb(255, 255, 200); // Amarillo (sistema)
            }
            else
            {
                mezuPanel.BackColor = Color.White; // Blanco (otros)
            }

            // Label con el nombre del remitente
            Label lblNor = new Label();
            lblNor.Text = nor;
            lblNor.Font = new Font("Segoe UI", 9, FontStyle.Bold);
            lblNor.ForeColor = Color.FromArgb(0, 120, 180);
            lblNor.AutoSize = true;
            lblNor.Location = new Point(5, 5);

            // Label con el mensaje
            Label lblMezua = new Label();
            lblMezua.Text = testua;
            lblMezua.Font = new Font("Segoe UI", 10);
            lblMezua.AutoSize = true;
            lblMezua.MaximumSize = new Size(panelMensajes.Width - 30, 0);
            lblMezua.Location = new Point(5, 22);

            mezuPanel.Controls.Add(lblNor);
            mezuPanel.Controls.Add(lblMezua);

            // Añadir al panel de mensajes (arriba para que el más nuevo esté abajo)
            panelMensajes.Controls.Add(mezuPanel);
            mezuPanel.BringToFront(); // Los nuevos arriba

            // Auto-scroll al final
            panelMensajes.ScrollControlIntoView(mezuPanel);
        }

        private void buttonEnviar_Click(object sender, EventArgs e)
        {
            string testua = textBoxMensaje.Text.Trim();
            if (string.IsNullOrEmpty(testua)) return;

            try
            {
                // Enviar formato: usuario$#/mensaje
                string mezuOsoa = $"{erabiltzailea}$#/{testua}";
                writer.WriteLine(mezuOsoa);

                textBoxMensaje.Clear();
                textBoxMensaje.Focus();
            }
            catch (Exception ex)
            {
                MessageBox.Show($"Ezin izan da mezua bidali: {ex.Message}", "Errorea");
            }
        }

        // Enviar con Enter
        private void textBoxMensaje_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar == (char)Keys.Enter)
            {
                e.Handled = true;
                buttonEnviar_Click(sender, e);
            }
        }

        private void Form2_FormClosing(object sender, FormClosingEventArgs e)
        {
            running = false;

            try
            {
                writer?.WriteLineAsync("IRTEN").Wait(1000);
            }
            catch { }

            try { reader?.Close(); } catch { }
            try { writer?.Close(); } catch { }
            try { stream?.Close(); } catch { }
            try { client?.Close(); } catch { }
        }

        // Eventos vacíos requeridos por el diseñador
        private void labelUsuario_Click(object sender, EventArgs e) { }
        private void textBoxMensaje_TextChanged(object sender, EventArgs e) { }
        private void dataGridViewIkasleak_CellContentClick(object sender, DataGridViewCellEventArgs e) { }
        private void dataGridViewTxandak_CellContentClick(object sender, DataGridViewCellEventArgs e) { }

        private void tableLayoutIkasleak_Paint(object sender, PaintEventArgs e)
        {

        }
    }
}