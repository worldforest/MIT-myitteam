package com.mit.returnDto;

import org.springframework.web.multipart.MultipartFile;

public class FeedinsertDto {
	String email;
	String description;
	String tags;
	String category;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "FeedinsertDto [email=" + email + ", description=" + description + ", tags=" + tags + ", category="
				+ category + "]";
	}

}
