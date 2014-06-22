package org.jboss.optaplanner.controller.model;

import java.io.Serializable;

public class TaskDef implements Serializable {

	private static final long serialVersionUID = -8349963947101031989L;
	private String id;
	private String name;
	private String progress;
	private String state;
	private String estimatedTime;
	private String ifPublic;
	private String owner;
	private String xmlfile;
	private boolean editable = false;

	private String renderStop;
	private String renderRun;
	private String renderPublish;
	private String renderUnpublish;
	private String renderEdit;
	private String renderDelete;
	private String renderCommand;
	private String link;

	public TaskDef(String id, String name, String state, String progress,
			String estimatedTime, String ifPublic, String owner,
			String xmlfile, String renderStop, String renderRun,
			String renderPublish, String renderUnpublish, String renderEdit,
			String renderDelete, String renderCommand) {
		this.id = id;
		this.name = name;
		this.state = state;
		this.progress = progress;
		this.estimatedTime = estimatedTime;
		this.ifPublic = ifPublic;
		this.owner = owner;
		this.xmlfile = xmlfile;
		this.renderDelete = renderDelete;
		this.renderEdit = renderEdit;
		this.renderStop = renderStop;
		this.renderRun = renderRun;
		this.renderPublish = renderPublish;
		this.renderUnpublish = renderUnpublish;
		this.renderCommand = renderCommand;
		this.link = "task/" + id ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProgress(String progress) {
		this.progress = progress;

	}

	public String getProgress() {
		return progress;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public String getEstimatedTime() {
		return estimatedTime;
	}

	public String getIfPublic() {
		return ifPublic;
	}

	public void setIfPublic(String ifPublic) {
		this.ifPublic = ifPublic;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public String getXmlFile() {
		return xmlfile;
	}

	public void setXmlfile(String xmlfile) {
		this.xmlfile = xmlfile;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public void setRenderEdit(String render) {
		this.renderEdit = render;
	}

	public String getRenderEdit() {
		return renderEdit;
	}

	public void setRenderRun(String render) {
		this.renderRun = render;
	}

	public String getRenderRun() {
		return renderRun;
	}

	public void setRenderStop(String render) {
		this.renderStop = render;
	}

	public String getRenderStop() {
		return renderStop;
	}

	public void setRenderPublish(String render) {
		this.renderPublish = render;
	}

	public String getRenderPublish() {
		return renderPublish;
	}

	public void setRenderUnpublosh(String render) {
		this.renderUnpublish = render;
	}

	public String getRenderUnpublish() {
		return renderUnpublish;
	}

	public void setRenderDelete(String render) {
		this.renderDelete = render;
	}

	public String getRenderDelete() {
		return renderDelete;
	}

	public String getRenderCommand() {
		return renderCommand;
	}

	public void setRenderCommand(String render) {
		this.renderCommand = render;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
