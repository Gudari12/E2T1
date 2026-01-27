namespace ChatForm
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;
        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            label1 = new Label();
            label2 = new Label();
            label3 = new Label();
            textBox1 = new TextBox();
            textBox2 = new TextBox();
            button1 = new Button();
            panel1 = new Panel();
            panel1.SuspendLayout();
            SuspendLayout();
            // 
            // label1
            // 
            label1.Font = new Font("Segoe UI", 24F, FontStyle.Bold);
            label1.ForeColor = Color.FromArgb(0, 120, 180);
            label1.Location = new Point(30, 25);
            label1.Name = "label1";
            label1.Size = new Size(340, 45);
            label1.TabIndex = 0;
            label1.Text = "ILEAPAINDEGI TXAT";
            label1.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Font = new Font("Segoe UI", 11F, FontStyle.Bold);
            label2.ForeColor = Color.FromArgb(50, 50, 50);
            label2.Location = new Point(30, 90);
            label2.Name = "label2";
            label2.Size = new Size(97, 20);
            label2.TabIndex = 1;
            label2.Text = "Erabiltzailea:";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Font = new Font("Segoe UI", 11F, FontStyle.Bold);
            label3.ForeColor = Color.FromArgb(50, 50, 50);
            label3.Location = new Point(30, 155);
            label3.Name = "label3";
            label3.Size = new Size(79, 20);
            label3.TabIndex = 2;
            label3.Text = "Pasahitza:";
            // 
            // textBox1
            // 
            textBox1.BackColor = Color.White;
            textBox1.BorderStyle = BorderStyle.FixedSingle;
            textBox1.Font = new Font("Segoe UI", 10F);
            textBox1.ForeColor = Color.FromArgb(80, 80, 80);
            textBox1.Location = new Point(30, 115);
            textBox1.Name = "textBox1";
            textBox1.PlaceholderText = "Introduce tu nombre de usuario";
            textBox1.Size = new Size(340, 25);
            textBox1.TabIndex = 3;
            // 
            // textBox2
            // 
            textBox2.BackColor = Color.White;
            textBox2.BorderStyle = BorderStyle.FixedSingle;
            textBox2.Font = new Font("Segoe UI", 10F);
            textBox2.ForeColor = Color.FromArgb(80, 80, 80);
            textBox2.Location = new Point(30, 180);
            textBox2.Name = "textBox2";
            textBox2.PlaceholderText = "Introduce tu contraseña";
            textBox2.Size = new Size(340, 25);
            textBox2.TabIndex = 4;
            textBox2.UseSystemPasswordChar = true;
            // 
            // button1
            // 
            button1.BackColor = Color.FromArgb(0, 120, 180);
            button1.Cursor = Cursors.Hand;
            button1.FlatAppearance.BorderSize = 0;
            button1.FlatStyle = FlatStyle.Flat;
            button1.Font = new Font("Segoe UI", 12F, FontStyle.Bold);
            button1.ForeColor = Color.White;
            button1.Location = new Point(30, 230);
            button1.Name = "button1";
            button1.Size = new Size(340, 40);
            button1.TabIndex = 5;
            button1.Text = "Hasi saioa";
            button1.UseVisualStyleBackColor = false;
            // 
            // panel1
            // 
            panel1.BackColor = Color.White;
            panel1.Controls.Add(label1);
            panel1.Controls.Add(label2);
            panel1.Controls.Add(label3);
            panel1.Controls.Add(textBox1);
            panel1.Controls.Add(textBox2);
            panel1.Controls.Add(button1);
            panel1.Location = new Point(206, 75);
            panel1.Name = "panel1";
            panel1.Size = new Size(400, 290);
            panel1.TabIndex = 6;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(190, 255, 255);
            ClientSize = new Size(800, 450);
            Controls.Add(panel1);
            Font = new Font("Segoe UI", 9F);
            Name = "Form1";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Login - Chat";
            panel1.ResumeLayout(false);
            panel1.PerformLayout();
            ResumeLayout(false);
        }
        #endregion
        private Label label1;
        private Label label2;
        private Label label3;
        private TextBox textBox1;
        private TextBox textBox2;
        private Button button1;
        private Panel panel1;
    }
}