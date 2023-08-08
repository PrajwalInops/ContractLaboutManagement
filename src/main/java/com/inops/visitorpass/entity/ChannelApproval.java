package com.inops.visitorpass.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ChannelApproval")
public class ChannelApproval {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long channelId;

	private String phoneNo;
	private String mailId;
	private boolean isApproved;
	private String visitorMobileNo;

}
