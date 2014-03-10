package org.bcc.cupboard.rest;

@XmlSeeAlso({
CustomerBean.class
})
@XmlType(propOrder = {"numResults", "entities"})
@XmlRootElement(name="CupboardWrapper")
public class EntityWrapper<T> implements Serializable {

  private List<T> entities;
  private int results;
  
  @XmlElement(name="data")
  public List<T> getEntities() {
    return entities;
  }
}
