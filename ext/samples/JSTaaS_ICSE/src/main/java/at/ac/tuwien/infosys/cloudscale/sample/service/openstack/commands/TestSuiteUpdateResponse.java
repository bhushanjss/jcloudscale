/*
   Copyright 2014 Philipp Leitner 

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
package at.ac.tuwien.infosys.jcloudscale.sample.service.openstack.commands;

import java.io.Serializable;

import at.ac.tuwien.infosys.jcloudscale.sample.service.TestStatus;

/**
 * State update of the test suite.
 */
public class TestSuiteUpdateResponse implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	private int suiteNumber;
	private TestStatus newStatus;
	
	public TestSuiteUpdateResponse(){}
	
	public TestSuiteUpdateResponse(TestStatus newStatus, int suiteNumber)
	{
		this.suiteNumber = suiteNumber;
		this.newStatus = newStatus;
	}
	
	public int getSuiteNumber() {
		return suiteNumber;
	}
	public void setSuiteNumber(int suiteNumber) {
		this.suiteNumber = suiteNumber;
	}
	
	public TestStatus getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(TestStatus newStatus) {
		this.newStatus = newStatus;
	}

	@Override
	public String toString() {
		return "TestSuiteUpdate: suite " + suiteNumber
				+ " is now " + newStatus + ".";
	}
}
