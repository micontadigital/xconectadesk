/*
 *  Copyright 2010-2011 BigData.mx
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package mx.gob.sat.cfd._3;

import java.util.Map;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

public final class NamespacePrefixMapperImpl extends NamespacePrefixMapper {
  
  private final Map<String, String> map;

  public NamespacePrefixMapperImpl(Map<String, String> map) {
    this.map = map;
  }

  public String getPreferredPrefix(String namespaceUri, String suggestion, 
                                   boolean requirePrefix) {
    String value = map.get(namespaceUri);
    return (value != null) ? value : suggestion;
  }
    
  public String[] getPreDeclaredNamespaceUris() {
    return new String[0];
  }
}