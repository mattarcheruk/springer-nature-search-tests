package connector;

class SpringerLinkConnectorException extends IllegalStateException
{

    SpringerLinkConnectorException(Throwable initialCause, String message)
    {
        super(message);
        this.initCause(initialCause);
    }

    SpringerLinkConnectorException(String message)
    {
        super(message);
    }

}