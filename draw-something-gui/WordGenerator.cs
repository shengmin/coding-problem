using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace ShengMin.DrawSomethingHelper {
	public class WordGenerator {
		private static readonly IDictionary<string, bool> _words;
		private const string PATH_WORDS = "words.txt";
		private const int SIZE_WORDS = 100000;

		private void GenerateWords(int size, char[] choices, bool[] used, char[] wordChars, int level, ICollection<string> words, IDictionary<string, bool> seen) {
			if (level == size) {
				string word = new string(wordChars);
				if (!seen.ContainsKey(word) && _words.ContainsKey(word)) {
					seen.Add(word, true);
					words.Add(word);
				}
				return;
			}

			for (int i = choices.Length - 1; i >= 0; i--) {
				if (used[i]) continue;
				used[i] = true;
				wordChars[level] = choices[i];
				GenerateWords(size, choices, used, wordChars, level + 1, words, seen);
				used[i] = false;
			}
		}

		public ICollection<string> GenerateWords(int size, char[] choices) {
			ICollection<string> words = new LinkedList<string>();
			GenerateWords(size, choices, new bool[choices.Length], new char[size], 0, words, new Dictionary<string, bool>());
			return words;
		}

		static WordGenerator(){
			_words = ReadWords();
		}

		private static IDictionary<string, bool> ReadWords() {
			IDictionary<string, bool> words = new Dictionary<string, bool>(SIZE_WORDS);
			using (TextReader rd = new StreamReader(new BufferedStream(File.OpenRead(PATH_WORDS)))) {
				for (string ln = rd.ReadLine(); ln != null; ln = rd.ReadLine()) {
					words.Add(ln, true);
				}
			}

			return words;
		}
	}
}
