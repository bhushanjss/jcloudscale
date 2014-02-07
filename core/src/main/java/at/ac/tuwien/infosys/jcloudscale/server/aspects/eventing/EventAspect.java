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
package at.ac.tuwien.infosys.jcloudscale.server.aspects.eventing;

import java.util.UUID;
import java.util.logging.Logger;

import at.ac.tuwien.infosys.jcloudscale.configuration.JCloudScaleConfiguration;
import at.ac.tuwien.infosys.jcloudscale.messaging.objects.monitoring.Event;
import at.ac.tuwien.infosys.jcloudscale.server.messaging.MonitoringMQHelper;

public abstract class EventAspect {
	
	protected static final Logger log = JCloudScaleConfiguration.getLogger(EventAspect.class);
	
	protected void initializeBaseEventProperties(Event event) {
		
		// TODO: given that we instantiate events ALL THE TIME, this is too much overhead!
		event.setEventId(UUID.randomUUID());
		
		event.setTimestamp(System.currentTimeMillis());
	}
	
	protected MonitoringMQHelper getMqHelper()
	{
		return MonitoringMQHelper.getInstance();
	}
}