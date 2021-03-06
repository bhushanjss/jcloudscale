/*
   Copyright 2013 Philipp Leitner

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package at.ac.tuwien.infosys.jcloudscale.test.testobject;

import java.io.Serializable;


public class MyComplexObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public String a;
	public String b;
	public byte[] c;
	
	@Override
	public boolean equals(Object other) {
		
		if(!(other instanceof MyComplexObject))
			return false;
		
		MyComplexObject otherComplex = (MyComplexObject)other;
		return
			(a.equals(otherComplex.a) &&
			 b.equals(otherComplex.b) &&
			 arrayEquals(c,otherComplex.c));
	}
	
	private boolean arrayEquals(byte[] a, byte[] b) {
		
		if(a == null && b == null)
			return true;
		if(a == null && b != null || a != null && b == null)
			return false;
		
		if(a.length != b.length)
			return false;
		
		for(int i=0; i<a.length; i++)
			if(a[i] != b[i])
				return false;
		
		return true;
		
	}
	
}
