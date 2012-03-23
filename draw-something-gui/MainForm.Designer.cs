namespace ShengMin.DrawSomethingHelper {
	partial class MainForm {
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing) {
			if (disposing && (components != null)) {
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent() {
			this._tbNLetter = new System.Windows.Forms.TextBox();
			this._tbLetters = new System.Windows.Forms.TextBox();
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this._tbWords = new System.Windows.Forms.TextBox();
			this.label3 = new System.Windows.Forms.Label();
			this._btnGenerate = new System.Windows.Forms.Button();
			this._worker = new System.ComponentModel.BackgroundWorker();
			this.SuspendLayout();
			// 
			// _tbNLetter
			// 
			this._tbNLetter.Location = new System.Drawing.Point(131, 22);
			this._tbNLetter.Name = "_tbNLetter";
			this._tbNLetter.Size = new System.Drawing.Size(174, 20);
			this._tbNLetter.TabIndex = 0;
			// 
			// _tbLetters
			// 
			this._tbLetters.Location = new System.Drawing.Point(131, 57);
			this._tbLetters.Name = "_tbLetters";
			this._tbLetters.Size = new System.Drawing.Size(174, 20);
			this._tbLetters.TabIndex = 1;
			// 
			// label1
			// 
			this.label1.AutoSize = true;
			this.label1.Location = new System.Drawing.Point(22, 22);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(91, 13);
			this.label1.TabIndex = 2;
			this.label1.Text = "Number of Letters";
			// 
			// label2
			// 
			this.label2.AutoSize = true;
			this.label2.Location = new System.Drawing.Point(25, 60);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(85, 13);
			this.label2.TabIndex = 3;
			this.label2.Text = "Available Letters";
			// 
			// _tbWords
			// 
			this._tbWords.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
			this._tbWords.Location = new System.Drawing.Point(131, 100);
			this._tbWords.Multiline = true;
			this._tbWords.Name = "_tbWords";
			this._tbWords.ReadOnly = true;
			this._tbWords.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
			this._tbWords.Size = new System.Drawing.Size(174, 192);
			this._tbWords.TabIndex = 4;
			// 
			// label3
			// 
			this.label3.AutoSize = true;
			this.label3.Location = new System.Drawing.Point(28, 100);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(80, 13);
			this.label3.TabIndex = 5;
			this.label3.Text = "Possible Words";
			// 
			// _btnGenerate
			// 
			this._btnGenerate.Location = new System.Drawing.Point(230, 309);
			this._btnGenerate.Name = "_btnGenerate";
			this._btnGenerate.Size = new System.Drawing.Size(75, 23);
			this._btnGenerate.TabIndex = 6;
			this._btnGenerate.Text = "Generate";
			this._btnGenerate.UseVisualStyleBackColor = true;
			this._btnGenerate.Click += new System.EventHandler(this.button1_Click);
			// 
			// _worker
			// 
			this._worker.DoWork += new System.ComponentModel.DoWorkEventHandler(this._worker_DoWork);
			this._worker.RunWorkerCompleted += new System.ComponentModel.RunWorkerCompletedEventHandler(this._worker_RunWorkerCompleted);
			// 
			// MainForm
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(333, 348);
			this.Controls.Add(this._btnGenerate);
			this.Controls.Add(this.label3);
			this.Controls.Add(this._tbWords);
			this.Controls.Add(this.label2);
			this.Controls.Add(this.label1);
			this.Controls.Add(this._tbLetters);
			this.Controls.Add(this._tbNLetter);
			this.Name = "MainForm";
			this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
			this.Text = "Draw Something Helper by ShengMin";
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.TextBox _tbNLetter;
		private System.Windows.Forms.TextBox _tbLetters;
		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.TextBox _tbWords;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Button _btnGenerate;
		private System.ComponentModel.BackgroundWorker _worker;
	}
}

