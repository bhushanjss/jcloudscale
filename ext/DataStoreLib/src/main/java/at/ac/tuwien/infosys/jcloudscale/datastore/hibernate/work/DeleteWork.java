/*
   Copyright 2013 Rene Nowak 

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
package at.ac.tuwien.infosys.jcloudscale.datastore.hibernate.work;

import at.ac.tuwien.infosys.jcloudscale.datastore.hibernate.dto.DatastoreFieldDto;
import at.ac.tuwien.infosys.jcloudscale.datastore.hibernate.model.DatastoreModel;
import org.hibernate.Session;

import java.lang.reflect.Field;
import java.util.List;

public class DeleteWork extends AbstractWork {

    private Object entity;

    public DeleteWork(Object entity) {
        this.entity = entity;
    }

    @Override
    public void doWork(Session session) {
        List<Field> datastoreFields = getNotNullDatastoreFields(entity);
        List<DatastoreFieldDto> datastoreFieldDtos = map(entity, datastoreFields);
        List<DatastoreModel> datastoreModels = getDatastoreModelsForEntity(session, entity);
        deleteFieldsFromDatastore(datastoreFieldDtos, datastoreModels);
        deleteDatastoreModels(session, datastoreModels);
    }

    private void deleteFieldsFromDatastore(List<DatastoreFieldDto> datastoreFieldDtos, List<DatastoreModel> datastoreModels) {
        for(DatastoreFieldDto datastoreFieldDto : datastoreFieldDtos) {
            DatastoreModel datastoreModel = getDatastoreModelForDto(datastoreModels, datastoreFieldDto);
            datastoreFieldHandler.delete(datastoreFieldDto, datastoreModel);
        }
    }

    private void deleteDatastoreModels(Session session, List<DatastoreModel> datastoreModels) {
        for(DatastoreModel datastoreModel : datastoreModels) {
            session.delete(datastoreModel);
        }
    }
}
