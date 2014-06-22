
package org.jboss.optaplanner.controller.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.jboss.optaplanner.service.server package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StartTask_QNAME = new QName("http://server.service.optaplanner.jboss.org/", "startTask");
    private final static QName _PauseTaskResponse_QNAME = new QName("http://server.service.optaplanner.jboss.org/", "pauseTaskResponse");
    private final static QName _StartTaskResponse_QNAME = new QName("http://server.service.optaplanner.jboss.org/", "startTaskResponse");
    private final static QName _CreateTaskResponse_QNAME = new QName("http://server.service.optaplanner.jboss.org/", "createTaskResponse");
    private final static QName _CreateTask_QNAME = new QName("http://server.service.optaplanner.jboss.org/", "createTask");
    private final static QName _PauseTask_QNAME = new QName("http://server.service.optaplanner.jboss.org/", "pauseTask");
    private final static QName _Exception_QNAME = new QName("http://server.service.optaplanner.jboss.org/", "Exception");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jboss.optaplanner.service.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link PauseTask }
     * 
     */
    public PauseTask createPauseTask() {
        return new PauseTask();
    }

    /**
     * Create an instance of {@link CreateTaskResponse }
     * 
     */
    public CreateTaskResponse createCreateTaskResponse() {
        return new CreateTaskResponse();
    }

    /**
     * Create an instance of {@link CreateTask }
     * 
     */
    public CreateTask createCreateTask() {
        return new CreateTask();
    }

    /**
     * Create an instance of {@link StartTaskResponse }
     * 
     */
    public StartTaskResponse createStartTaskResponse() {
        return new StartTaskResponse();
    }

    /**
     * Create an instance of {@link StartTask }
     * 
     */
    public StartTask createStartTask() {
        return new StartTask();
    }

    /**
     * Create an instance of {@link PauseTaskResponse }
     * 
     */
    public PauseTaskResponse createPauseTaskResponse() {
        return new PauseTaskResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.service.optaplanner.jboss.org/", name = "startTask")
    public JAXBElement<StartTask> createStartTask(StartTask value) {
        return new JAXBElement<StartTask>(_StartTask_QNAME, StartTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PauseTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.service.optaplanner.jboss.org/", name = "pauseTaskResponse")
    public JAXBElement<PauseTaskResponse> createPauseTaskResponse(PauseTaskResponse value) {
        return new JAXBElement<PauseTaskResponse>(_PauseTaskResponse_QNAME, PauseTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.service.optaplanner.jboss.org/", name = "startTaskResponse")
    public JAXBElement<StartTaskResponse> createStartTaskResponse(StartTaskResponse value) {
        return new JAXBElement<StartTaskResponse>(_StartTaskResponse_QNAME, StartTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.service.optaplanner.jboss.org/", name = "createTaskResponse")
    public JAXBElement<CreateTaskResponse> createCreateTaskResponse(CreateTaskResponse value) {
        return new JAXBElement<CreateTaskResponse>(_CreateTaskResponse_QNAME, CreateTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.service.optaplanner.jboss.org/", name = "createTask")
    public JAXBElement<CreateTask> createCreateTask(CreateTask value) {
        return new JAXBElement<CreateTask>(_CreateTask_QNAME, CreateTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PauseTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.service.optaplanner.jboss.org/", name = "pauseTask")
    public JAXBElement<PauseTask> createPauseTask(PauseTask value) {
        return new JAXBElement<PauseTask>(_PauseTask_QNAME, PauseTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.service.optaplanner.jboss.org/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

}
