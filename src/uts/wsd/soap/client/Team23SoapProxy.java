package uts.wsd.soap.client;

public class Team23SoapProxy implements uts.wsd.soap.client.Team23Soap {
  private String _endpoint = null;
  private uts.wsd.soap.client.Team23Soap team23Soap = null;
  
  public Team23SoapProxy() {
    _initTeam23SoapProxy();
  }
  
  public Team23SoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initTeam23SoapProxy();
  }
  
  private void _initTeam23SoapProxy() {
    try {
      team23Soap = (new uts.wsd.soap.client.Team23SoapServiceLocator()).getTeam23SoapPort();
      if (team23Soap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)team23Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)team23Soap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (team23Soap != null)
      ((javax.xml.rpc.Stub)team23Soap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public uts.wsd.soap.client.Team23Soap getTeam23Soap() {
    if (team23Soap == null)
      _initTeam23SoapProxy();
    return team23Soap;
  }
  
  public uts.wsd.soap.client.Poll[] getPolls(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, int arg4) throws java.rmi.RemoteException{
    if (team23Soap == null)
      _initTeam23SoapProxy();
    return team23Soap.getPolls(arg0, arg1, arg2, arg3, arg4);
  }
  
  
}