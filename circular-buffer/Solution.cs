/**
 * @author ShengMin Zhang
 * @problem Circular Buffer
 */

using System;
using System.IO;
using System.Text;
using System.Collections;
using System.Collections.Generic;
 
public class CircularBuffer<T>: IEnumerable<T> {
  private readonly T[] buffer;
  private readonly int capacity;
  /** Number of items in the circular buffer */
  private int count = 0;
  private int readIndex = 0;
  private int addIndex = 0;

  public CircularBuffer(int capacity) {
    this.capacity = capacity;
    this.buffer = new T[capacity];
  }
  
  public IEnumerator<T> GetEnumerator() {
    for(int readCount = 0, i = readIndex; readCount < count; i++, readCount++) {
      yield return buffer[i % capacity];
    }
  }
  
  /** 
   * Appends the element to the end of circular buffer 
   * @param element the element to add
   */
  public void Add(T element) {
    if(capacity != 0) {
      buffer[addIndex] = element;
      addIndex = (addIndex + 1) % capacity;
      if(count < capacity) {
        // No element needs to be removed
        count++;
      } else {
        // Remove the first element
        readIndex = (readIndex + 1) % capacity;
      }
    }
  }
  
  /** 
   * Removes the first n elements
   * @param n the number of elements to remove
   */
  public void RemoveMany(int n) {
    count -= n;
    readIndex = (readIndex + n) % capacity;
  }
  
  public override String ToString() {
    StringBuilder builder = new StringBuilder();
    foreach(T element in buffer) {
      builder.Append(element);
      builder.Append(", ");
    }
    return String.Format("readIndex: {0}, addIndex: {1}, elements: [{2}]", readIndex, addIndex, builder);
  }
  
  IEnumerator IEnumerable.GetEnumerator() {
    return this.GetEnumerator();
  }
}
 
public class Solution {
  public static void Main(string[] args) {
    using(TextReader reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
    using(TextWriter writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()))) {
      CircularBuffer<string> buffer = new CircularBuffer<string>(int.Parse(reader.ReadLine()));
      bool toQuit = false;
      
      while(!toQuit) {
        string[] ln = reader.ReadLine().Split(' ');
        switch(ln[0]) {
          case "Q":
            toQuit = true;
            break;
          case "A":
            Add(reader, buffer, int.Parse(ln[1]));
            break;
          case "R":
            Remove(buffer, int.Parse(ln[1]));
            break;
          case "L":
            List(buffer, writer);
            break;
        }
      }
    }
  }
  
  private static void List(CircularBuffer<string> buffer, TextWriter writer) {
    foreach(string element in buffer) {
      writer.WriteLine(element);
    }
  }
  
  private static void Remove(CircularBuffer<string> buffer, int n) {
    buffer.RemoveMany(n);
  }
  
  private static void Add(TextReader reader, CircularBuffer<string> buffer, int n) {
    for(int i = 0; i < n; i++) {
      buffer.Add(reader.ReadLine());
    }
  }
}
