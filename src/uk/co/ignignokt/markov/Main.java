/*
 * The MIT License
 *
 * Copyright 2011 Andrew Etches <andy@ignignokt.co.uk>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package uk.co.ignignokt.markov;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

import uk.co.ignignokt.markov.external.*;

public class Main {
	public static void main(String[] args) throws IOException{
		Markov wm = new Markov();
		
		FileInputStream fis = new FileInputStream("data.txt");
		UnicodeReader ur = new UnicodeReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(ur);
		
		String line;
		StringBuilder builder = new StringBuilder();
		
		while((line = br.readLine()) != null){
			if(line.equals("")){
				wm.addSentence(builder.toString());
				builder = new StringBuilder();
				continue;
			} else {
				builder.append(line);
				builder.append(' ');
			}
		}
		
		wm.addSentence(builder.toString());
		
                System.out.println(wm.getStructure());
		System.out.println(wm.getSentence());
	}
}
