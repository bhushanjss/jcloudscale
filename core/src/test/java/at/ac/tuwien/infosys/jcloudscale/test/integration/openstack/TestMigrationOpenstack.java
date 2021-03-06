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
package at.ac.tuwien.infosys.jcloudscale.test.integration.openstack;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import at.ac.tuwien.infosys.jcloudscale.annotations.JCloudScaleShutdown;
import at.ac.tuwien.infosys.jcloudscale.configuration.JCloudScaleConfiguration;
import at.ac.tuwien.infosys.jcloudscale.management.CloudManager;
import at.ac.tuwien.infosys.jcloudscale.test.util.ConfigurationHelper;
import at.ac.tuwien.infosys.jcloudscale.vm.JCloudScaleClient;
import at.ac.tuwien.infosys.jcloudscale.vm.IHost;
import at.ac.tuwien.infosys.jcloudscale.vm.IVirtualHostPool;

public class TestMigrationOpenstack extends at.ac.tuwien.infosys.jcloudscale.test.integration.base.TestMigration
{
	private static OpenstackHelper staticInstanceManager;
	
	@BeforeClass
	public static void setup() throws Exception 
	{
		//
		// Initializing JCloudScale
		//
		JCloudScaleConfiguration cfg = ConfigurationHelper.createDefaultCloudTestConfiguration().build();

		JCloudScaleClient.setConfiguration(cfg);
		
		//
		// Starting "static" hosts.
		//
		staticInstanceManager = new OpenstackHelper(cfg);
		staticInstanceManager.startStaticInstances();
		
		cs = CloudManager.getInstance();
	}
	
	@AfterClass
	@JCloudScaleShutdown
	public static void tearDown() throws Exception 
	{
		staticInstanceManager.shudownStaticInstances();
	}
	
	@After
	public void cleanup() throws Exception
	{
		IVirtualHostPool pool = cs.getHostPool();
		for(IHost host : pool.getHosts())
			pool.shutdownHost(host.getId());
	}

}
