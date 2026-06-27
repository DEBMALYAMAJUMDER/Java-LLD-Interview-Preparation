package ratelimitter.entity;

public class ClientRequests {
    private String clientId;
    private String clientName;

    public ClientRequests(String clientId, String clientName) {
        this.clientId = clientId;
        this.clientName = clientName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public String toString() {
        return "ClientRequests{" +
                "clientId='" + clientId + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
