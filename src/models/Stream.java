package models;

public class Stream {
    private int streamId;
    private String streamName;
    
    public Stream(int streamId, String streamName) {
        this.streamId = streamId;
        this.streamName = streamName;

    }
    
	public int getstreamId() {
		return streamId;
	}
	public void setEnrollmentId(int streamId) {
		this.streamId = streamId;
	}
	public String getstreamName() {
		return streamName;
	}
	public void setstreamName(String streamName) {
		this.streamName = streamName;
	}
	
    @Override
    public String toString() {
        return streamName + " (ID: " + streamId + ")";
    }
}
