package org.acme.conference.speaker;

import java.util.UUID;

import org.acme.conference.speaker.idgenerator.IdGenerator;

public class DeterministicIdGenerator implements IdGenerator {

    private UUID nextUUID=new UUID(0, 0);

    public String generate() {
        UUID result = nextUUID;
        nextUUID=null;
        return result.toString();
    }
    
    public void setNextUUID(final UUID nextUUID){
        this.nextUUID=nextUUID;
    }
    
}
