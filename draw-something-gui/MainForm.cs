using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;

namespace ShengMin.DrawSomethingHelper {
	public partial class MainForm : Form {

		private class WorkerArgument {
			public int Size { get; set; }
			public char[] Choices { get; set; }
		}

		public MainForm() {
			InitializeComponent();
		}

		private void button1_Click(object sender, EventArgs e) {
			_btnGenerate.Enabled = false;

			string rawNLetters = _tbNLetter.Text;
			char[] rawLetters = _tbLetters.Text.ToCharArray();

			StringBuilder sb = new StringBuilder(rawLetters.Length);
			for (int i = 0; i < rawLetters.Length; i++) {
				char c = rawLetters[i];
				if (c >= 'a' && c <= 'z') sb.Append(c); 
				else if(c >= 'A' && c <= 'Z') sb.Append(c - 'A' + 'a');
			}

			try {
				int n = int.Parse(rawNLetters);
				_worker.RunWorkerAsync(new WorkerArgument() { Choices = sb.ToString().ToCharArray(), Size = n });
			} catch (Exception ex) {
				_btnGenerate.Enabled = true;
			} 
			
		}

		private void _worker_DoWork(object sender, DoWorkEventArgs e) {
			WorkerArgument arg = e.Argument as WorkerArgument;
			ICollection<string> words = new WordGenerator().GenerateWords(arg.Size, arg.Choices);
			string[] wordsArray = new string[words.Count];
			words.CopyTo(wordsArray, 0);
			e.Result = wordsArray;
		}

		private void _worker_RunWorkerCompleted(object sender, RunWorkerCompletedEventArgs e) {
			string[] words = e.Result as string[];
			_tbWords.Lines = words;
			_btnGenerate.Enabled = true;
		}
	}
}
