namespace ChatForm
{
    partial class Form2
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            DataGridViewCellStyle dataGridViewCellStyle1 = new DataGridViewCellStyle();
            DataGridViewCellStyle dataGridViewCellStyle2 = new DataGridViewCellStyle();
            DataGridViewCellStyle dataGridViewCellStyle3 = new DataGridViewCellStyle();
            DataGridViewCellStyle dataGridViewCellStyle4 = new DataGridViewCellStyle();
            DataGridViewCellStyle dataGridViewCellStyle5 = new DataGridViewCellStyle();
            tableLayoutPanelMain = new TableLayoutPanel();
            panelIkasleak = new Panel();
            dataGridViewTxandak = new DataGridView();
            dataGridViewIkasleak = new DataGridView();
            labelIkasleak = new Label();
            panelChat = new Panel();
            panelMensajes = new Panel();
            textBoxMensaje = new TextBox();
            buttonEnviar = new Button();
            labelChat = new Label();
            labelUsuario = new Label();
            tableLayoutIkasleak = new TableLayoutPanel();
            tableLayoutPanelMain.SuspendLayout();
            panelIkasleak.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)dataGridViewTxandak).BeginInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewIkasleak).BeginInit();
            panelChat.SuspendLayout();
            SuspendLayout();
            // 
            // tableLayoutPanelMain
            // 
            tableLayoutPanelMain.ColumnCount = 2;
            tableLayoutPanelMain.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 66.67F));
            tableLayoutPanelMain.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 33.33F));
            tableLayoutPanelMain.Controls.Add(panelIkasleak, 0, 0);
            tableLayoutPanelMain.Controls.Add(panelChat, 1, 0);
            tableLayoutPanelMain.Dock = DockStyle.Fill;
            tableLayoutPanelMain.Location = new Point(0, 50);
            tableLayoutPanelMain.Name = "tableLayoutPanelMain";
            tableLayoutPanelMain.Padding = new Padding(20, 10, 20, 20);
            tableLayoutPanelMain.RowCount = 1;
            tableLayoutPanelMain.RowStyles.Add(new RowStyle(SizeType.Percent, 100F));
            tableLayoutPanelMain.Size = new Size(1264, 631);
            tableLayoutPanelMain.TabIndex = 0;
            // 
            // panelIkasleak
            // 
            panelIkasleak.BackColor = Color.White;
            panelIkasleak.Controls.Add(tableLayoutIkasleak);  // ← Nuevo TableLayoutPanel
            panelIkasleak.Controls.Add(labelIkasleak);
            panelIkasleak.Dock = DockStyle.Fill;
            panelIkasleak.Location = new Point(23, 13);
            panelIkasleak.Name = "panelIkasleak";
            panelIkasleak.Padding = new Padding(20);
            panelIkasleak.Size = new Size(810, 595);
            panelIkasleak.TabIndex = 0;

            // 
            // tableLayoutIkasleak (NUEVO)
            // 
            tableLayoutIkasleak.ColumnCount = 1;
            tableLayoutIkasleak.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100F));
            tableLayoutIkasleak.Dock = DockStyle.Fill;
            tableLayoutIkasleak.Location = new Point(20, 70);  // Debajo del label
            tableLayoutIkasleak.Name = "tableLayoutIkasleak";
            tableLayoutIkasleak.RowCount = 2;
            tableLayoutIkasleak.RowStyles.Add(new RowStyle(SizeType.Percent, 50F));
            tableLayoutIkasleak.RowStyles.Add(new RowStyle(SizeType.Percent, 50F));
            tableLayoutIkasleak.Size = new Size(770, 505);  // 595 - 70 (label) - 20 (margen)
            tableLayoutIkasleak.TabIndex = 3;

            // 
            // dataGridViewIkasleak 
            // 
            dataGridViewIkasleak.AllowUserToAddRows = false;
            dataGridViewIkasleak.AllowUserToDeleteRows = false;
            dataGridViewIkasleak.Dock = DockStyle.Fill;  // ← Cambiado de Anchor a Dock
            dataGridViewIkasleak.BackgroundColor = Color.White;
            dataGridViewIkasleak.BorderStyle = BorderStyle.None;
            dataGridViewCellStyle4.Alignment = DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle4.BackColor = SystemColors.Control;
            dataGridViewCellStyle4.Font = new Font("Segoe UI", 9F);
            dataGridViewCellStyle4.ForeColor = SystemColors.WindowText;
            dataGridViewCellStyle4.SelectionBackColor = SystemColors.Highlight;
            dataGridViewCellStyle4.SelectionForeColor = SystemColors.HighlightText;
            dataGridViewCellStyle4.WrapMode = DataGridViewTriState.True;
            dataGridViewIkasleak.ColumnHeadersDefaultCellStyle = dataGridViewCellStyle4;
            dataGridViewIkasleak.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewCellStyle5.Alignment = DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle5.BackColor = SystemColors.Window;
            dataGridViewCellStyle5.Font = new Font("Segoe UI", 9F);
            dataGridViewCellStyle5.ForeColor = SystemColors.ControlText;
            dataGridViewCellStyle5.SelectionBackColor = SystemColors.Highlight;
            dataGridViewCellStyle5.SelectionForeColor = SystemColors.HighlightText;
            dataGridViewCellStyle5.WrapMode = DataGridViewTriState.False;
            dataGridViewIkasleak.DefaultCellStyle = dataGridViewCellStyle5;
            dataGridViewIkasleak.Location = new Point(0, 0);  // ← Dentro del TableLayout
            dataGridViewIkasleak.Name = "dataGridViewIkasleak";
            dataGridViewIkasleak.ReadOnly = true;
            dataGridViewIkasleak.ScrollBars = ScrollBars.Both;  // ← Añadido scroll
            dataGridViewIkasleak.Size = new Size(770, 252);
            dataGridViewIkasleak.TabIndex = 1;
            dataGridViewIkasleak.CellContentClick += dataGridViewIkasleak_CellContentClick;

            // 
            // dataGridViewTxandak 
            // 
            dataGridViewTxandak.AllowUserToAddRows = false;
            dataGridViewTxandak.AllowUserToDeleteRows = false;
            dataGridViewTxandak.Dock = DockStyle.Fill;  // ← Cambiado de Anchor a Dock
            dataGridViewTxandak.BackgroundColor = Color.White;
            dataGridViewTxandak.BorderStyle = BorderStyle.None;
            dataGridViewCellStyle1.Alignment = DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle1.BackColor = SystemColors.Control;
            dataGridViewCellStyle1.Font = new Font("Segoe UI", 9F);
            dataGridViewCellStyle1.ForeColor = SystemColors.WindowText;
            dataGridViewCellStyle1.SelectionBackColor = SystemColors.Highlight;
            dataGridViewCellStyle1.SelectionForeColor = SystemColors.HighlightText;
            dataGridViewCellStyle1.WrapMode = DataGridViewTriState.True;
            dataGridViewTxandak.ColumnHeadersDefaultCellStyle = dataGridViewCellStyle1;
            dataGridViewTxandak.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewCellStyle2.Alignment = DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle2.BackColor = SystemColors.Window;
            dataGridViewCellStyle2.Font = new Font("Segoe UI", 9F);
            dataGridViewCellStyle2.ForeColor = SystemColors.ControlText;
            dataGridViewCellStyle2.SelectionBackColor = SystemColors.Highlight;
            dataGridViewCellStyle2.SelectionForeColor = SystemColors.HighlightText;
            dataGridViewCellStyle2.WrapMode = DataGridViewTriState.False;
            dataGridViewTxandak.DefaultCellStyle = dataGridViewCellStyle2;
            dataGridViewTxandak.Location = new Point(0, 0);  // ← Dentro del TableLayout
            dataGridViewTxandak.Name = "dataGridViewTxandak";
            dataGridViewTxandak.ReadOnly = true;
            dataGridViewCellStyle3.Alignment = DataGridViewContentAlignment.MiddleLeft;
            dataGridViewCellStyle3.BackColor = SystemColors.Control;
            dataGridViewCellStyle3.Font = new Font("Segoe UI", 9F);
            dataGridViewCellStyle3.ForeColor = SystemColors.WindowText;
            dataGridViewCellStyle3.SelectionBackColor = SystemColors.Highlight;
            dataGridViewCellStyle3.SelectionForeColor = SystemColors.HighlightText;
            dataGridViewCellStyle3.WrapMode = DataGridViewTriState.True;
            dataGridViewTxandak.RowHeadersDefaultCellStyle = dataGridViewCellStyle3;
            dataGridViewTxandak.ScrollBars = ScrollBars.Both;  // ← Añadido scroll
            dataGridViewTxandak.Size = new Size(770, 252);
            dataGridViewTxandak.TabIndex = 2;
            dataGridViewTxandak.CellContentClick += dataGridViewTxandak_CellContentClick;
            // 
            // labelIkasleak
            // 
            labelIkasleak.Dock = DockStyle.Top;
            labelIkasleak.Font = new Font("Segoe UI", 24F, FontStyle.Bold);
            labelIkasleak.ForeColor = Color.FromArgb(0, 120, 180);
            labelIkasleak.Location = new Point(20, 20);
            labelIkasleak.Name = "labelIkasleak";
            labelIkasleak.Size = new Size(770, 50);
            labelIkasleak.TabIndex = 0;
            labelIkasleak.Text = "IKASLEAK ETA TXANDAK";
            labelIkasleak.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // panelChat
            // 
            panelChat.BackColor = Color.White;
            panelChat.Controls.Add(panelMensajes);
            panelChat.Controls.Add(textBoxMensaje);
            panelChat.Controls.Add(buttonEnviar);
            panelChat.Controls.Add(labelChat);
            panelChat.Dock = DockStyle.Fill;
            panelChat.Location = new Point(839, 13);
            panelChat.Name = "panelChat";
            panelChat.Padding = new Padding(20);
            panelChat.Size = new Size(402, 595);
            panelChat.TabIndex = 1;
            // 
            // panelMensajes
            // 
            panelMensajes.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            panelMensajes.AutoScroll = true;
            panelMensajes.BackColor = Color.FromArgb(245, 245, 245);
            panelMensajes.BorderStyle = BorderStyle.FixedSingle;
            panelMensajes.Location = new Point(20, 80);
            panelMensajes.Name = "panelMensajes";
            panelMensajes.Size = new Size(362, 430);
            panelMensajes.TabIndex = 2;
            // 
            // textBoxMensaje
            // 
            textBoxMensaje.Anchor = AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            textBoxMensaje.BackColor = Color.White;
            textBoxMensaje.BorderStyle = BorderStyle.FixedSingle;
            textBoxMensaje.Font = new Font("Segoe UI", 11F);
            textBoxMensaje.ForeColor = Color.FromArgb(80, 80, 80);
            textBoxMensaje.Location = new Point(20, 525);
            textBoxMensaje.Name = "textBoxMensaje";
            textBoxMensaje.PlaceholderText = " Idatzi zure mezua hemen...";
            textBoxMensaje.Size = new Size(272, 27);
            textBoxMensaje.TabIndex = 3;
            textBoxMensaje.TextChanged += textBoxMensaje_TextChanged;
            textBoxMensaje.KeyPress += textBoxMensaje_KeyPress;

            // 
            // buttonEnviar
            // 
            buttonEnviar.Anchor = AnchorStyles.Bottom | AnchorStyles.Right;
            buttonEnviar.BackColor = Color.FromArgb(0, 120, 180);
            buttonEnviar.Cursor = Cursors.Hand;
            buttonEnviar.FlatAppearance.BorderSize = 0;
            buttonEnviar.FlatStyle = FlatStyle.Flat;
            buttonEnviar.Font = new Font("Segoe UI", 11F, FontStyle.Bold);
            buttonEnviar.ForeColor = Color.White;
            buttonEnviar.Location = new Point(302, 520);
            buttonEnviar.Name = "buttonEnviar";
            buttonEnviar.Size = new Size(80, 35);
            buttonEnviar.TabIndex = 4;
            buttonEnviar.Text = "Bidali";
            buttonEnviar.UseVisualStyleBackColor = false;
            buttonEnviar.Click += buttonEnviar_Click;
            // 
            // labelChat
            // 
            labelChat.Dock = DockStyle.Top;
            labelChat.Font = new Font("Segoe UI", 24F, FontStyle.Bold);
            labelChat.ForeColor = Color.FromArgb(0, 120, 180);
            labelChat.Location = new Point(20, 20);
            labelChat.Name = "labelChat";
            labelChat.Size = new Size(362, 50);
            labelChat.TabIndex = 0;
            labelChat.Text = "TXATA";
            labelChat.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // labelUsuario
            // 
            labelUsuario.Dock = DockStyle.Top;
            labelUsuario.Font = new Font("Segoe UI", 18F, FontStyle.Bold);
            labelUsuario.ForeColor = Color.FromArgb(50, 50, 50);
            labelUsuario.Location = new Point(0, 0);
            labelUsuario.Name = "labelUsuario";
            labelUsuario.Padding = new Padding(0, 10, 0, 0);
            labelUsuario.Size = new Size(1264, 50);
            labelUsuario.TabIndex = 1;
            labelUsuario.Text = "Ongi etorri, erabiltzailea!";
            labelUsuario.TextAlign = ContentAlignment.MiddleLeft;
            labelUsuario.Click += labelUsuario_Click;
            // 
            // Form2
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(190, 255, 255);
            ClientSize = new Size(1264, 681);
            Controls.Add(tableLayoutPanelMain);
            Controls.Add(labelUsuario);
            Font = new Font("Segoe UI", 9F);
            Name = "Form2";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Txat";
            WindowState = FormWindowState.Maximized;
            Load += Form2_Load;
            FormClosing += Form2_FormClosing;
            tableLayoutPanelMain.ResumeLayout(false);
            panelIkasleak.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)dataGridViewTxandak).EndInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewIkasleak).EndInit();
            panelChat.ResumeLayout(false);
            panelChat.PerformLayout();
            ResumeLayout(false);
        }

        #endregion
        private TableLayoutPanel tableLayoutPanelMain;
        private Panel panelIkasleak;
        private DataGridView dataGridViewIkasleak;
        private Label labelIkasleak;
        private Panel panelChat;
        private Panel panelMensajes;
        private TextBox textBoxMensaje;
        private Button buttonEnviar;
        private Label labelChat;
        private Label labelUsuario;
        private DataGridView dataGridViewTxandak;
        private TableLayoutPanel tableLayoutIkasleak;
    }
}