package com.opl.mudra.api.mca.cubictree.report.json;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Report {
	@JsonProperty(value = "title")
	protected String title;
	@JsonProperty(value = "request-details")
	protected RequestDetails requestDetails;

	protected Summary summary;
	
	@JsonProperty(value = "supreme-court")
	protected SupremeCourt supremeCourt;
	
	@JsonProperty(value = "high-court")
	protected HighCourt highCourt;
	
	@JsonProperty(value = "district-court")
	protected DistrictCourt districtCourt;
	
	@JsonProperty(value = "high-risk-court")
	protected HighRiskCourt highRiskCourt;
	
	@JsonProperty(value = "tribunal-cases")
	protected TribunalCases tribunalCases;
	
	@JsonProperty(value = "defaulter-cases")
	protected DefaulterCases defaulterCases;

	/**
	 * Gets the value of the title property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the value of the title property.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setTitle(String value) {
		this.title = value;
	}

	/**
	 * Gets the value of the requestDetails property.
	 * 
	 * @return possible object is {@link RequestDetails }
	 * 
	 */
	public RequestDetails getRequestDetails() {
		return requestDetails;
	}

	/**
	 * Sets the value of the requestDetails property.
	 * 
	 * @param value allowed object is {@link RequestDetails }
	 * 
	 */
	public void setRequestDetails(RequestDetails value) {
		this.requestDetails = value;
	}

	/**
	 * Gets the value of the summary property.
	 * 
	 * @return possible object is {@link Summary }
	 * 
	 */
	public Summary getSummary() {
		return summary;
	}

	/**
	 * Sets the value of the summary property.
	 * 
	 * @param value allowed object is {@link Summary }
	 * 
	 */
	public void setSummary(Summary value) {
		this.summary = value;
	}

	/**
	 * Gets the value of the supremeCourt property.
	 * 
	 * @return possible object is {@link SupremeCourt }
	 * 
	 */
	public SupremeCourt getSupremeCourt() {
		return supremeCourt;
	}

	/**
	 * Sets the value of the supremeCourt property.
	 * 
	 * @param value allowed object is {@link SupremeCourt }
	 * 
	 */
	public void setSupremeCourt(SupremeCourt value) {
		this.supremeCourt = value;
	}

	/**
	 * Gets the value of the highCourt property.
	 * 
	 * @return possible object is {@link HighCourt }
	 * 
	 */
	public HighCourt getHighCourt() {
		return highCourt;
	}

	/**
	 * Sets the value of the highCourt property.
	 * 
	 * @param value allowed object is {@link HighCourt }
	 * 
	 */
	public void setHighCourt(HighCourt value) {
		this.highCourt = value;
	}

	/**
	 * Gets the value of the districtCourt property.
	 * 
	 * @return possible object is {@link DistrictCourt }
	 * 
	 */
	public DistrictCourt getDistrictCourt() {
		return districtCourt;
	}

	/**
	 * Sets the value of the districtCourt property.
	 * 
	 * @param value allowed object is {@link DistrictCourt }
	 * 
	 */
	public void setDistrictCourt(DistrictCourt value) {
		this.districtCourt = value;
	}

	/**
	 * Gets the value of the highRiskCourt property.
	 * 
	 * @return possible object is {@link HighRiskCourt }
	 * 
	 */
	public HighRiskCourt getHighRiskCourt() {
		return highRiskCourt;
	}

	/**
	 * Sets the value of the highRiskCourt property.
	 * 
	 * @param value allowed object is {@link HighRiskCourt }
	 * 
	 */
	public void setHighRiskCourt(HighRiskCourt value) {
		this.highRiskCourt = value;
	}

	/**
	 * Gets the value of the tribunalCases property.
	 * 
	 * @return possible object is {@link TribunalCases }
	 * 
	 */
	public TribunalCases getTribunalCases() {
		return tribunalCases;
	}

	/**
	 * Sets the value of the tribunalCases property.
	 * 
	 * @param value allowed object is {@link TribunalCases }
	 * 
	 */
	public void setTribunalCases(TribunalCases value) {
		this.tribunalCases = value;
	}

	/**
	 * Gets the value of the defaulterCases property.
	 * 
	 * @return possible object is {@link DefaulterCases }
	 * 
	 */
	public DefaulterCases getDefaulterCases() {
		return defaulterCases;
	}

	/**
	 * Sets the value of the defaulterCases property.
	 * 
	 * @param value allowed object is {@link DefaulterCases }
	 * 
	 */
	public void setDefaulterCases(DefaulterCases value) {
		this.defaulterCases = value;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class DefaulterCases {

		protected Trading trading;

		protected Nse nse;

		protected Willful willful;

		protected Statutory statutory;

		/**
		 * Gets the value of the trading property.
		 * 
		 * @return possible object is {@link Trading }
		 * 
		 */
		public Trading getTrading() {
			return trading;
		}

		/**
		 * Sets the value of the trading property.
		 * 
		 * @param value allowed object is {@link Trading }
		 * 
		 */
		public void setTrading(Trading value) {
			this.trading = value;
		}

		/**
		 * Gets the value of the nse property.
		 * 
		 * @return possible object is {@link Nse }
		 * 
		 */
		public Nse getNse() {
			return nse;
		}

		/**
		 * Sets the value of the nse property.
		 * 
		 * @param value allowed object is {@link Nse }
		 * 
		 */
		public void setNse(Nse value) {
			this.nse = value;
		}

		/**
		 * Gets the value of the willful property.
		 * 
		 * @return possible object is {@link Willful }
		 * 
		 */
		public Willful getWillful() {
			return willful;
		}

		/**
		 * Sets the value of the willful property.
		 * 
		 * @param value allowed object is {@link Willful }
		 * 
		 */
		public void setWillful(Willful value) {
			this.willful = value;
		}

		/**
		 * Gets the value of the statutory property.
		 * 
		 * @return possible object is {@link Statutory }
		 * 
		 */
		public Statutory getStatutory() {
			return statutory;
		}

		/**
		 * Sets the value of the statutory property.
		 * 
		 * @param value allowed object is {@link Statutory }
		 * 
		 */
		public void setStatutory(Statutory value) {
			this.statutory = value;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Nse {

			protected short count;

			/**
			 * Gets the value of the count property.
			 * 
			 */
			public short getCount() {
				return count;
			}

			/**
			 * Sets the value of the count property.
			 * 
			 */
			public void setCount(short value) {
				this.count = value;
			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Statutory {

			protected short count;

			/**
			 * Gets the value of the count property.
			 * 
			 */
			public short getCount() {
				return count;
			}

			/**
			 * Sets the value of the count property.
			 * 
			 */
			public void setCount(short value) {
				this.count = value;
			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Trading {

			protected short count;

			/**
			 * Gets the value of the count property.
			 * 
			 */
			public short getCount() {
				return count;
			}

			/**
			 * Sets the value of the count property.
			 * 
			 */
			public void setCount(short value) {
				this.count = value;
			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Willful {

			protected short count;

			/**
			 * Gets the value of the count property.
			 * 
			 */
			public short getCount() {
				return count;
			}

			/**
			 * Sets the value of the count property.
			 * 
			 */
			public void setCount(short value) {
				this.count = value;
			}

		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class DistrictCourt {
		protected By by;

		protected Against against;

		/**
		 * Gets the value of the by property.
		 * 
		 * @return possible object is {@link By }
		 * 
		 */
		public By getBy() {
			return by;
		}

		/**
		 * Sets the value of the by property.
		 * 
		 * @param value allowed object is {@link By }
		 * 
		 */
		public void setBy(By value) {
			this.by = value;
		}

		/**
		 * Gets the value of the against property.
		 * 
		 * @return possible object is {@link Against }
		 * 
		 */
		public Against getAgainst() {
			return against;
		}

		/**
		 * Sets the value of the against property.
		 * 
		 * @param value allowed object is {@link Against }
		 * 
		 */
		public void setAgainst(Against value) {
			this.against = value;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		public static class Against {

			protected Criminal criminal;

			protected Civil civil;

			protected Others others;

			/**
			 * Gets the value of the criminal property.
			 * 
			 * @return possible object is {@link Criminal }
			 * 
			 */
			public Criminal getCriminal() {
				return criminal;
			}

			/**
			 * Sets the value of the criminal property.
			 * 
			 * @param value allowed object is {@link Criminal }
			 * 
			 */
			public void setCriminal(Criminal value) {
				this.criminal = value;
			}

			/**
			 * Gets the value of the civil property.
			 * 
			 * @return possible object is {@link Civil }
			 * 
			 */
			public Civil getCivil() {
				return civil;
			}

			/**
			 * Sets the value of the civil property.
			 * 
			 * @param value allowed object is {@link Civil }
			 * 
			 */
			public void setCivil(Civil value) {
				this.civil = value;
			}

			/**
			 * Gets the value of the others property.
			 * 
			 * @return possible object is {@link Others }
			 * 
			 */
			public Others getOthers() {
				return others;
			}

			/**
			 * Sets the value of the others property.
			 * 
			 * @param value allowed object is {@link Others }
			 * 
			 */
			public void setOthers(Others value) {
				this.others = value;
			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Civil {

				protected short live;

				protected List<Cases> cases;

				protected short disposed;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}
					public void setRecord(List<Record> record) {
						this.record = record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")
						protected Object caseYear;
						@JsonProperty(value = "case-status")
						protected Object caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected Object act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseYear(Object value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseStatus(Object value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setAct(Object value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Criminal {

				protected short live;

				protected List<Cases> cases;

				protected short disposed;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						protected short srNo;
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")
						protected Object caseYear;
						@JsonProperty(value = "case-status")
						protected Object caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected Object act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseYear(Object value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseStatus(Object value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setAct(Object value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Others {

				protected short live;

				protected short disposed;

				@JsonProperty(value = "cases")
				protected List<Cases> cases;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {
					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}
					public void setRecord(List<Record> record) {
						this.record = record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")

						protected int caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected String act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 */
						public int getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 */
						public void setCaseYear(int value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setAct(String value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		public static class By {
			protected Criminal criminal;

			protected Civil civil;

			protected Others others;

			/**
			 * Gets the value of the criminal property.
			 * 
			 * @return possible object is {@link Criminal }
			 * 
			 */
			public Criminal getCriminal() {
				return criminal;
			}

			/**
			 * Sets the value of the criminal property.
			 * 
			 * @param value allowed object is {@link Criminal }
			 * 
			 */
			public void setCriminal(Criminal value) {
				this.criminal = value;
			}

			/**
			 * Gets the value of the civil property.
			 * 
			 * @return possible object is {@link Civil }
			 * 
			 */
			public Civil getCivil() {
				return civil;
			}

			/**
			 * Sets the value of the civil property.
			 * 
			 * @param value allowed object is {@link Civil }
			 * 
			 */
			public void setCivil(Civil value) {
				this.civil = value;
			}

			/**
			 * Gets the value of the others property.
			 * 
			 * @return possible object is {@link Others }
			 * 
			 */
			public Others getOthers() {
				return others;
			}

			/**
			 * Sets the value of the others property.
			 * 
			 * @param value allowed object is {@link Others }
			 * 
			 */
			public void setOthers(Others value) {
				this.others = value;
			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Civil {

				protected short live;

				protected List<Cases> cases;

				protected short disposed;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}
				public List<Cases> getCases() {
					return cases;
				}
				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}
				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					public void setRecord(List<Record> record) {
						this.record = record;
					}

					protected List<Record> record;
 
					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")
						protected Object caseYear;
						@JsonProperty(value = "case-status")
						protected Object caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected Object act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseYear(Object value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseStatus(Object value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setAct(Object value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Criminal {

				protected short live;

				protected List<Cases> cases;

				protected short disposed;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					public void setRecord(List<Record> record) {
						this.record = record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")
						protected String caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected String act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseYear(String value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setAct(String value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Others {

				protected short live;

				protected short disposed;

				protected List<Cases> cases;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}
					public void setRecord(List<Record> record) {
						this.record = record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)

					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")

						protected int caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected Object act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 */
						public int getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 */
						public void setCaseYear(int value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setAct(Object value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class HighCourt {

		protected By by;

		protected Against against;

		/**
		 * Gets the value of the by property.
		 * 
		 * @return possible object is {@link By }
		 * 
		 */
		public By getBy() {
			return by;
		}

		/**
		 * Sets the value of the by property.
		 * 
		 * @param value allowed object is {@link By }
		 * 
		 */
		public void setBy(By value) {
			this.by = value;
		}

		/**
		 * Gets the value of the against property.
		 * 
		 * @return possible object is {@link Against }
		 * 
		 */
		public Against getAgainst() {
			return against;
		}

		/**
		 * Sets the value of the against property.
		 * 
		 * @param value allowed object is {@link Against }
		 * 
		 */
		public void setAgainst(Against value) {
			this.against = value;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		public static class Against {

			protected Criminal criminal;

			protected Civil civil;

			protected Others others;

			/**
			 * Gets the value of the criminal property.
			 * 
			 * @return possible object is {@link Criminal }
			 * 
			 */
			public Criminal getCriminal() {
				return criminal;
			}

			/**
			 * Sets the value of the criminal property.
			 * 
			 * @param value allowed object is {@link Criminal }
			 * 
			 */
			public void setCriminal(Criminal value) {
				this.criminal = value;
			}

			/**
			 * Gets the value of the civil property.
			 * 
			 * @return possible object is {@link Civil }
			 * 
			 */
			public Civil getCivil() {
				return civil;
			}

			/**
			 * Sets the value of the civil property.
			 * 
			 * @param value allowed object is {@link Civil }
			 * 
			 */
			public void setCivil(Civil value) {
				this.civil = value;
			}

			/**
			 * Gets the value of the others property.
			 * 
			 * @return possible object is {@link Others }
			 * 
			 */
			public Others getOthers() {
				return others;
			}

			/**
			 * Sets the value of the others property.
			 * 
			 * @param value allowed object is {@link Others }
			 * 
			 */
			public void setOthers(Others value) {
				this.others = value;
			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Civil {

				protected short live;

				protected List<Cases> cases;

				protected short disposed;

				protected short unknown;

				public short getLive() {
					return live;
				}

				public void setLive(short live) {
					this.live = live;
				}
				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				public short getDisposed() {
					return disposed;
				}

				public void setDisposed(short disposed) {
					this.disposed = disposed;
				}

				public short getUnknown() {
					return unknown;
				}

				public void setUnknown(short unknown) {
					this.unknown = unknown;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")

						protected Object caseNo;
						@JsonProperty(value = "case-year")

						protected int caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected String act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						

						public Object getCaseNo() {
							return caseNo;
						}

						public void setCaseNo(Object caseNo) {
							this.caseNo = caseNo;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 */
						public int getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 */
						public void setCaseYear(int value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setAct(String value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Criminal {

				protected short live;

				protected short disposed;

				protected List<Cases> cases;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}
				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")

						protected Object caseNo;
						@JsonProperty(value = "case-year")

						protected int caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected String act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

					

						public Object getCaseNo() {
							return caseNo;
						}

						public void setCaseNo(Object caseNo) {
							this.caseNo = caseNo;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 */
						public int getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 */
						public void setCaseYear(int value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setAct(String value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Others {

				protected short live;

				protected List<Cases> cases;

				protected short disposed;

				protected short unknown;

				public short getLive() {
					return live;
				}

				public void setLive(short live) {
					this.live = live;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				public short getDisposed() {
					return disposed;
				}

				public void setDisposed(short disposed) {
					this.disposed = disposed;
				}

				public short getUnknown() {
					return unknown;
				}

				public void setUnknown(short unknown) {
					this.unknown = unknown;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")

						protected Object caseNo;
						@JsonProperty(value = "case-year")

						protected int caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected String act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						public Object getCaseNo() {
							return caseNo;
						}

						public void setCaseNo(Object caseNo) {
							this.caseNo = caseNo;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 */
						public int getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 */
						public void setCaseYear(int value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setAct(String value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		public static class By {

			protected Criminal criminal;

			protected Civil civil;

			protected Others others;

			/**
			 * Gets the value of the criminal property.
			 * 
			 * @return possible object is {@link Criminal }
			 * 
			 */
			public Criminal getCriminal() {
				return criminal;
			}

			/**
			 * Sets the value of the criminal property.
			 * 
			 * @param value allowed object is {@link Criminal }
			 * 
			 */
			public void setCriminal(Criminal value) {
				this.criminal = value;
			}

			/**
			 * Gets the value of the civil property.
			 * 
			 * @return possible object is {@link Civil }
			 * 
			 */
			public Civil getCivil() {
				return civil;
			}

			/**
			 * Sets the value of the civil property.
			 * 
			 * @param value allowed object is {@link Civil }
			 * 
			 */
			public void setCivil(Civil value) {
				this.civil = value;
			}

			/**
			 * Gets the value of the others property.
			 * 
			 * @return possible object is {@link Others }
			 * 
			 */
			public Others getOthers() {
				return others;
			}

			/**
			 * Sets the value of the others property.
			 * 
			 * @param value allowed object is {@link Others }
			 * 
			 */
			public void setOthers(Others value) {
				this.others = value;
			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Civil {
				protected short live;

				protected List<Cases> cases;

				protected short disposed;

				protected short unknown;

				public short getLive() {
					return live;
				}

				public void setLive(short live) {
					this.live = live;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				public short getDisposed() {
					return disposed;
				}

				public void setDisposed(short disposed) {
					this.disposed = disposed;
				}

				public short getUnknown() {
					return unknown;
				}

				public void setUnknown(short unknown) {
					this.unknown = unknown;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")

						protected Object caseNo;
						@JsonProperty(value = "case-year")

						protected int caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected String act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						

						public Object getCaseNo() {
							return caseNo;
						}

						public void setCaseNo(Object caseNo) {
							this.caseNo = caseNo;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 */
						public int getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 */
						public void setCaseYear(int value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setAct(String value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Criminal {

				protected short live;

				protected short disposed;

				protected List<Cases> cases;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}
				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")

						protected Object caseNo;
						@JsonProperty(value = "case-year")

						protected int caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected String act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						public Object getCaseNo() {
							return caseNo;
						}

						public void setCaseNo(Object caseNo) {
							this.caseNo = caseNo;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 */
						public int getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 */
						public void setCaseYear(int value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setAct(String value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Others {
				protected short live;

				protected List<Cases> cases;

				protected short disposed;

				protected short unknown;

				public short getLive() {
					return live;
				}

				public void setLive(short live) {
					this.live = live;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				public short getDisposed() {
					return disposed;
				}

				public void setDisposed(short disposed) {
					this.disposed = disposed;
				}

				public short getUnknown() {
					return unknown;
				}

				public void setUnknown(short unknown) {
					this.unknown = unknown;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")

						protected Object caseNo;
						@JsonProperty(value = "case-year")

						protected int caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected String act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected String petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected String respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						
						public Object getCaseNo() {
							return caseNo;
						}

						public void setCaseNo(Object caseNo) {
							this.caseNo = caseNo;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 */
						public int getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 */
						public void setCaseYear(int value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setAct(String value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerAdvocate(String value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentAdvocate(String value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class HighRiskCourt {

		protected Drt drt;

		protected Drat drat;

		protected Nclt nclt;

		protected Nclat nclat;

		/**
		 * Gets the value of the drt property.
		 * 
		 * @return possible object is {@link Drt }
		 * 
		 */
		public Drt getDrt() {
			return drt;
		}

		/**
		 * Sets the value of the drt property.
		 * 
		 * @param value allowed object is {@link Drt }
		 * 
		 */
		public void setDrt(Drt value) {
			this.drt = value;
		}

		/**
		 * Gets the value of the drat property.
		 * 
		 * @return possible object is {@link Drat }
		 * 
		 */
		public Drat getDrat() {
			return drat;
		}

		/**
		 * Sets the value of the drat property.
		 * 
		 * @param value allowed object is {@link Drat }
		 * 
		 */
		public void setDrat(Drat value) {
			this.drat = value;
		}

		/**
		 * Gets the value of the nclt property.
		 * 
		 * @return possible object is {@link Nclt }
		 * 
		 */
		public Nclt getNclt() {
			return nclt;
		}

		/**
		 * Sets the value of the nclt property.
		 * 
		 * @param value allowed object is {@link Nclt }
		 * 
		 */
		public void setNclt(Nclt value) {
			this.nclt = value;
		}

		/**
		 * Gets the value of the nclat property.
		 * 
		 * @return possible object is {@link Nclat }
		 * 
		 */
		public Nclat getNclat() {
			return nclat;
		}

		/**
		 * Sets the value of the nclat property.
		 * 
		 * @param value allowed object is {@link Nclat }
		 * 
		 */
		public void setNclat(Nclat value) {
			this.nclat = value;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Drat {

			protected short live;

			protected short disposed;

			protected short unknown;
			
			protected List<Cases>cases;
			

			public List<Cases> getCases() {
				return cases;
			}

			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}

			/**
			 * Gets the value of the live property.
			 * 
			 */
			public short getLive() {
				return live;
			}

			/**
			 * Sets the value of the live property.
			 * 
			 */
			public void setLive(short value) {
				this.live = value;
			}

			/**
			 * Gets the value of the disposed property.
			 * 
			 */
			public short getDisposed() {
				return disposed;
			}

			/**
			 * Sets the value of the disposed property.
			 * 
			 */
			public void setDisposed(short value) {
				this.disposed = value;
			}

			/**
			 * Gets the value of the unknown property.
			 * 
			 */
			public short getUnknown() {
				return unknown;
			}

			/**
			 * Sets the value of the unknown property.
			 * 
			 */
			public void setUnknown(short value) {
				this.unknown = value;
			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					@JsonProperty(value = "case-no")

					protected Object caseNo;
					@JsonProperty(value = "case-year")

					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Drt {

			protected short live;

			protected short disposed;

			protected short unknown;
			
			protected List<Cases> cases;

			public List<Cases> getCases() {
				return cases;
			}

			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}

			/**
			 * Gets the value of the live property.
			 * 
			 */
			public short getLive() {
				return live;
			}

			/**
			 * Sets the value of the live property.
			 * 
			 */
			public void setLive(short value) {
				this.live = value;
			}

			/**
			 * Gets the value of the disposed property.
			 * 
			 */
			public short getDisposed() {
				return disposed;
			}

			/**
			 * Sets the value of the disposed property.
			 * 
			 */
			public void setDisposed(short value) {
				this.disposed = value;
			}

			/**
			 * Gets the value of the unknown property.
			 * 
			 */
			public short getUnknown() {
				return unknown;
			}

			/**
			 * Sets the value of the unknown property.
			 * 
			 */
			public void setUnknown(short value) {
				this.unknown = value;
			}
			
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					@JsonProperty(value = "case-no")

					protected Object caseNo;
					@JsonProperty(value = "case-year")

					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

					
					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Nclat {

			protected short live;

			protected short disposed;

			protected short unknown;
			
			protected List<Cases>cases;

			public List<Cases> getCases() {
				return cases;
			}

			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}

			/**
			 * Gets the value of the live property.
			 * 
			 */
			public short getLive() {
				return live;
			}

			/**
			 * Sets the value of the live property.
			 * 
			 */
			public void setLive(short value) {
				this.live = value;
			}

			/**
			 * Gets the value of the disposed property.
			 * 
			 */
			public short getDisposed() {
				return disposed;
			}

			/**
			 * Sets the value of the disposed property.
			 * 
			 */
			public void setDisposed(short value) {
				this.disposed = value;
			}

			/**
			 * Gets the value of the unknown property.
			 * 
			 */
			public short getUnknown() {
				return unknown;
			}

			/**
			 * Sets the value of the unknown property.
			 * 
			 */
			public void setUnknown(short value) {
				this.unknown = value;
			}
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					@JsonProperty(value = "case-no")

					protected Object caseNo;
					@JsonProperty(value = "case-year")

					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

					

					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Nclt {

			protected short live;

			protected short disposed;

			protected short unknown;
			
			protected List<Cases>cases;

			public List<Cases> getCases() {
				return cases;
			}
			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}

			/**
			 * Gets the value of the live property.
			 * 
			 */
			public short getLive() {
				return live;
			}

			/**
			 * Sets the value of the live property.
			 * 
			 */
			public void setLive(short value) {
				this.live = value;
			}

			/**
			 * Gets the value of the disposed property.
			 * 
			 */
			public short getDisposed() {
				return disposed;
			}

			/**
			 * Sets the value of the disposed property.
			 * 
			 */
			public void setDisposed(short value) {
				this.disposed = value;
			}

			/**
			 * Gets the value of the unknown property.
			 * 
			 */
			public short getUnknown() {
				return unknown;
			}

			/**
			 * Sets the value of the unknown property.
			 * 
			 */
			public void setUnknown(short value) {
				this.unknown = value;
			}
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					@JsonProperty(value = "case-no")

					protected Object caseNo;
					@JsonProperty(value = "case-year")

					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

				

					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}

		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	public static class RequestDetails {
		@JsonProperty(value = "report-requested-by", defaultValue = "")
		protected Object reportRequestedBy;
		@JsonProperty(value = "cin-no", defaultValue = "")
		protected Object cinNo;
		@JsonProperty(value = "report-date", defaultValue = "")
		protected String reportDate;
		@JsonProperty(value = "report-for-company", defaultValue = "")
		protected Object reportForCompany;
		@JsonProperty(value = "loan-amount", defaultValue = "")
		protected Object loanAmount;
		@JsonProperty(value = "address", defaultValue = "")
		protected Object address;
		@JsonProperty(value = "loan-product", defaultValue = "")
		protected Object loanProduct;
		@JsonProperty(value = "keywords", defaultValue = "")
		protected String keywords;

		/**
		 * Gets the value of the reportRequestedBy property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getReportRequestedBy() {
			return reportRequestedBy;
		}

		/**
		 * Sets the value of the reportRequestedBy property.
		 * 
		 * @param value allowed object is {@link Object }
		 * 
		 */
		public void setReportRequestedBy(Object value) {
			this.reportRequestedBy = value;
		}

		/**
		 * Gets the value of the cinNo property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getCinNo() {
			return cinNo;
		}

		/**
		 * Sets the value of the cinNo property.
		 * 
		 * @param value allowed object is {@link Object }
		 * 
		 */
		public void setCinNo(Object value) {
			this.cinNo = value;
		}

		/**
		 * Gets the value of the reportDate property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getReportDate() {
			return reportDate;
		}

		/**
		 * Sets the value of the reportDate property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setReportDate(String value) {
			this.reportDate = value;
		}

		/**
		 * Gets the value of the reportForCompany property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getReportForCompany() {
			return reportForCompany;
		}

		/**
		 * Sets the value of the reportForCompany property.
		 * 
		 * @param value allowed object is {@link Object }
		 * 
		 */
		public void setReportForCompany(Object value) {
			this.reportForCompany = value;
		}

		/**
		 * Gets the value of the loanAmount property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getLoanAmount() {
			return loanAmount;
		}

		/**
		 * Sets the value of the loanAmount property.
		 * 
		 * @param value allowed object is {@link Object }
		 * 
		 */
		public void setLoanAmount(Object value) {
			this.loanAmount = value;
		}

		/**
		 * Gets the value of the address property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getAddress() {
			return address;
		}

		/**
		 * Sets the value of the address property.
		 * 
		 * @param value allowed object is {@link Object }
		 * 
		 */
		public void setAddress(Object value) {
			this.address = value;
		}

		/**
		 * Gets the value of the loanProduct property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getLoanProduct() {
			return loanProduct;
		}

		/**
		 * Sets the value of the loanProduct property.
		 * 
		 * @param value allowed object is {@link Object }
		 * 
		 */
		public void setLoanProduct(Object value) {
			this.loanProduct = value;
		}

		/**
		 * Gets the value of the keywords property.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getKeywords() {
			return keywords;
		}

		/**
		 * Sets the value of the keywords property.
		 * 
		 * @param value allowed object is {@link String }
		 * 
		 */
		public void setKeywords(String value) {
			this.keywords = value;
		}

		@Override
		public String toString() {
			return "RequestDetails [reportRequestedBy=" + reportRequestedBy + ", cinNo=" + cinNo + ", reportDate="
					+ reportDate + ", reportForCompany=" + reportForCompany + ", loanAmount=" + loanAmount
					+ ", address=" + address + ", loanProduct=" + loanProduct + ", keywords=" + keywords + "]";
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	public static class Summary {

		@JsonProperty(value = "supreme-court")
		protected short supremeCourt;
		@JsonProperty(value = "high-court")
		protected short highCourt;
		@JsonProperty(value = "district-court")
		protected short districtCourt;
		
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		@JsonProperty(value = "consumer-court")
		protected Object consumerCourt;
		
		@JsonProperty(value = "cestat")
		protected short cestat;
		
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		@JsonProperty(value = "nclt")
		protected Object nclt;
		
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		@JsonProperty(value = "nclat")
		protected Object nclat;
		
		@JsonProperty(value = "itat")
		protected short itat;
		
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		@JsonProperty(value = "drt")
		protected Object drt;

		/**
		 * Gets the value of the supremeCourt property.
		 * 
		 */
		public short getSupremeCourt() {
			return supremeCourt;
		}

		/**
		 * Sets the value of the supremeCourt property.
		 * 
		 */
		public void setSupremeCourt(short value) {
			this.supremeCourt = value;
		}

		/**
		 * Gets the value of the highCourt property.
		 * 
		 */
		public short getHighCourt() {
			return highCourt;
		}

		/**
		 * Sets the value of the highCourt property.
		 * 
		 */
		public void setHighCourt(short value) {
			this.highCourt = value;
		}

		/**
		 * Gets the value of the districtCourt property.
		 * 
		 */
		public short getDistrictCourt() {
			return districtCourt;
		}

		/**
		 * Sets the value of the districtCourt property.
		 * 
		 */
		public void setDistrictCourt(short value) {
			this.districtCourt = value;
		}

		/**
		 * Gets the value of the consumerCourt property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getConsumerCourt() {
			return consumerCourt;
		}

		/**
		 * Sets the value of the consumerCourt property.
		 * 
		 * @param value allowed object is {@link Object }
		 * 
		 */
		public void setConsumerCourt(Object value) {
			this.consumerCourt = value;
		}

		/**
		 * Gets the value of the cestat property.
		 * 
		 */
		public short getCestat() {
			return cestat;
		}

		/**
		 * Sets the value of the cestat property.
		 * 
		 */
		public void setCestat(short value) {
			this.cestat = value;
		}

		/**
		 * Gets the value of the nclt property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getNclt() {
			return nclt;
		}

		/**
		 * Sets the value of the nclt property.
		 * 
		 * @param value allowed object is {@link Object }
		 * 
		 */
		public void setNclt(Object value) {
			this.nclt = value;
		}

		/**
		 * Gets the value of the nclat property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getNclat() {
			return nclat;
		}

		/**
		 * Sets the value of the nclat property.
		 * 
		 * @param value allowed object is {@link Object }
		 * 
		 */
		public void setNclat(Object value) {
			this.nclat = value;
		}

		/**
		 * Gets the value of the itat property.
		 * 
		 */
		public short getItat() {
			return itat;
		}

		/**
		 * Sets the value of the itat property.
		 * 
		 */
		public void setItat(short value) {
			this.itat = value;
		}

		/**
		 * Gets the value of the drt property.
		 * 
		 * @return possible object is {@link Object }
		 * 
		 */
		public Object getDrt() {
			return drt;
		}

		/**
		 * Sets the value of the drt property.
		 * 
		 * @param value allowed object is {@link Object }
		 * 
		 */
		public void setDrt(Object value) {
			this.drt = value;
		}

		@Override
		public String toString() {
			return "Summary [supremeCourt=" + supremeCourt + ", highCourt=" + highCourt + ", districtCourt="
					+ districtCourt + ", consumerCourt=" + consumerCourt + ", cestat=" + cestat + ", nclt=" + nclt
					+ ", nclat=" + nclat + ", itat=" + itat + ", drt=" + drt + "]";
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class SupremeCourt {
		protected By by;
		protected Against against;

		/**
		 * Gets the value of the by property.
		 * 
		 * @return possible object is {@link By }
		 * 
		 */
		public By getBy() {
			return by;
		}

		/**
		 * Sets the value of the by property.
		 * 
		 * @param value allowed object is {@link By }
		 * 
		 */
		public void setBy(By value) {
			this.by = value;
		}

		/**
		 * Gets the value of the against property.
		 * 
		 * @return possible object is {@link Against }
		 * 
		 */
		public Against getAgainst() {
			return against;
		}

		/**
		 * Sets the value of the against property.
		 * 
		 * @param value allowed object is {@link Against }
		 * 
		 */
		public void setAgainst(Against value) {
			this.against = value;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_EMPTY)
		public static class Against {

			protected Criminal criminal;

			protected Civil civil;

			protected Others others;

			/**
			 * Gets the value of the criminal property.
			 * 
			 * @return possible object is {@link Criminal }
			 * 
			 */
			public Criminal getCriminal() {
				return criminal;
			}

			/**
			 * Sets the value of the criminal property.
			 * 
			 * @param value allowed object is {@link Criminal }
			 * 
			 */
			public void setCriminal(Criminal value) {
				this.criminal = value;
			}

			/**
			 * Gets the value of the civil property.
			 * 
			 * @return possible object is {@link Civil }
			 * 
			 */
			public Civil getCivil() {
				return civil;
			}

			/**
			 * Sets the value of the civil property.
			 * 
			 * @param value allowed object is {@link Civil }
			 * 
			 */
			public void setCivil(Civil value) {
				this.civil = value;
			}

			/**
			 * Gets the value of the others property.
			 * 
			 * @return possible object is {@link Others }
			 * 
			 */
			public Others getOthers() {
				return others;
			}

			/**
			 * Sets the value of the others property.
			 * 
			 * @param value allowed object is {@link Others }
			 * 
			 */
			public void setOthers(Others value) {
				this.others = value;
			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Civil {

				protected short live;

				protected short disposed;

				protected short unknown;

				
				protected List<Cases> cases;
				
				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}
				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Civil> record;

					public List<Civil> getRecord() {
						return record;
					}
					public void setRecord(List<Civil> record) {
						this.record = record;
					}
					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")
						protected Object caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected Object act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected String petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected String respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseYear(Object value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setAct(Object value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerAddress(String value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentAddress(String value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Criminal {

				protected short live;

				protected short disposed;

				protected List<Cases> cases;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}
				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected Object state;

						protected Object district;

						protected Object court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")
						protected Object caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected Object act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setState(Object value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setDistrict(Object value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCourt(Object value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseYear(Object value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setAct(Object value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Others {

				protected short live;

				protected short disposed;

				protected List<Cases> cases;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						return record;
					}

					public void setRecord(List<Record> record) {
						this.record = record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")
						protected Object caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected Object act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected String petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected String respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseYear(Object value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setAct(Object value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerAddress(String value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentAddress(String value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class By {
			
			protected Criminal criminal;
			protected Civil civil;
			protected Others others;
			

			/**
			 * Gets the value of the criminal property.
			 * 
			 * @return possible object is {@link Criminal }
			 * 
			 */
			public Criminal getCriminal() {
				return criminal;
			}

			/**
			 * Sets the value of the criminal property.
			 * 
			 * @param value allowed object is {@link Criminal }
			 * 
			 */
			public void setCriminal(Criminal value) {
				this.criminal = value;
			}

			/**
			 * Gets the value of the civil property.
			 * 
			 * @return possible object is {@link Civil }
			 * 
			 */
			public Civil getCivil() {
				return civil;
			}

			/**
			 * Sets the value of the civil property.
			 * 
			 * @param value allowed object is {@link Civil }
			 * 
			 */
			public void setCivil(Civil value) {
				this.civil = value;
			}

			/**
			 * Gets the value of the others property.
			 * 
			 * @return possible object is {@link Others }
			 * 
			 */
			public Others getOthers() {
				return others;
			}

			/**
			 * Sets the value of the others property.
			 * 
			 * @param value allowed object is {@link Others }
			 * 
			 */
			public void setOthers(Others value) {
				this.others = value;
			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Civil {

				protected short live;

				protected short disposed;

				protected short unknown;
				
				protected List<Cases> cases;
				
				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

				
					public List<Record> getRecord() {
						return record;
					}
					public void setRecord(List<Record> record) {
						this.record = record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")
						protected Object caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected Object act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected String petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected String respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseYear(Object value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setAct(Object value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerAddress(String value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentAddress(String value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}
				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Criminal {
				
				protected short live;

				protected short disposed;

				protected short unknown;

				protected List<Cases> cases;
				
				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;


					public List<Record> getRecord() {
						return record;
					}
					public void setRecord(List<Record> record) {
						this.record = record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected String state;

						protected String district;

						protected String court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")
						protected Object caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected Object act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected String petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected String respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setState(String value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setDistrict(String value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCourt(String value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseYear(Object value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setAct(Object value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerAddress(String value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentAddress(String value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}
				
				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_DEFAULT)
			public static class Others {

				protected short live;

				protected short disposed;

				protected List<Cases> cases;

				protected short unknown;

				/**
				 * Gets the value of the live property.
				 * 
				 */
				public short getLive() {
					return live;
				}

				/**
				 * Sets the value of the live property.
				 * 
				 */
				public void setLive(short value) {
					this.live = value;
				}

				/**
				 * Gets the value of the disposed property.
				 * 
				 */
				public short getDisposed() {
					return disposed;
				}

				/**
				 * Sets the value of the disposed property.
				 * 
				 */
				public void setDisposed(short value) {
					this.disposed = value;
				}

				public List<Cases> getCases() {
					return cases;
				}

				public void setCases(List<Cases> cases) {
					this.cases = cases;
				}

				/**
				 * Gets the value of the unknown property.
				 * 
				 */
				public short getUnknown() {
					return unknown;
				}

				/**
				 * Sets the value of the unknown property.
				 * 
				 */
				public void setUnknown(short value) {
					this.unknown = value;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Cases {

					protected List<Record> record;

					public List<Record> getRecord() {
						if (record == null) {
							record = new ArrayList<Record>();
						}
						return this.record;
					}

					@JsonIgnoreProperties(ignoreUnknown = true)
					@JsonInclude(JsonInclude.Include.NON_EMPTY)
					public static class Record {

						@JsonProperty(value = "sr-no")
						@JsonInclude(JsonInclude.Include.NON_DEFAULT)
						protected short srNo;

						protected String type;

						protected Object state;

						protected Object district;

						protected Object court;
						@JsonProperty(value = "case-no")
						protected String caseNo;
						@JsonProperty(value = "case-year")
						protected Object caseYear;
						@JsonProperty(value = "case-status")
						protected String caseStatus;
						@JsonProperty(value = "by-or-against")
						protected String byOrAgainst;

						protected Object act;
						@JsonProperty(value = "petitioner-name")
						protected String petitionerName;
						@JsonProperty(value = "petitioner-address")
						protected Object petitionerAddress;
						@JsonProperty(value = "petitioner-advocate")
						protected Object petitionerAdvocate;
						@JsonProperty(value = "respondent-name")
						protected String respondentName;
						@JsonProperty(value = "respondent-address")
						protected Object respondentAddress;
						@JsonProperty(value = "respondent-advocate")
						protected Object respondentAdvocate;

						/**
						 * Gets the value of the srNo property.
						 * 
						 */
						public short getSrNo() {
							return srNo;
						}

						/**
						 * Sets the value of the srNo property.
						 * 
						 */
						public void setSrNo(short value) {
							this.srNo = value;
						}

						/**
						 * Gets the value of the type property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getType() {
							return type;
						}

						/**
						 * Sets the value of the type property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setType(String value) {
							this.type = value;
						}

						/**
						 * Gets the value of the state property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getState() {
							return state;
						}

						/**
						 * Sets the value of the state property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setState(Object value) {
							this.state = value;
						}

						/**
						 * Gets the value of the district property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getDistrict() {
							return district;
						}

						/**
						 * Sets the value of the district property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setDistrict(Object value) {
							this.district = value;
						}

						/**
						 * Gets the value of the court property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCourt() {
							return court;
						}

						/**
						 * Sets the value of the court property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCourt(Object value) {
							this.court = value;
						}

						/**
						 * Gets the value of the caseNo property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseNo() {
							return caseNo;
						}

						/**
						 * Sets the value of the caseNo property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseNo(String value) {
							this.caseNo = value;
						}

						/**
						 * Gets the value of the caseYear property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getCaseYear() {
							return caseYear;
						}

						/**
						 * Sets the value of the caseYear property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setCaseYear(Object value) {
							this.caseYear = value;
						}

						/**
						 * Gets the value of the caseStatus property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCaseStatus() {
							return caseStatus;
						}

						/**
						 * Sets the value of the caseStatus property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setCaseStatus(String value) {
							this.caseStatus = value;
						}

						/**
						 * Gets the value of the byOrAgainst property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getByOrAgainst() {
							return byOrAgainst;
						}

						/**
						 * Sets the value of the byOrAgainst property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setByOrAgainst(String value) {
							this.byOrAgainst = value;
						}

						/**
						 * Gets the value of the act property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getAct() {
							return act;
						}

						/**
						 * Sets the value of the act property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setAct(Object value) {
							this.act = value;
						}

						/**
						 * Gets the value of the petitionerName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getPetitionerName() {
							return petitionerName;
						}

						/**
						 * Sets the value of the petitionerName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setPetitionerName(String value) {
							this.petitionerName = value;
						}

						/**
						 * Gets the value of the petitionerAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAddress() {
							return petitionerAddress;
						}

						/**
						 * Sets the value of the petitionerAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAddress(Object value) {
							this.petitionerAddress = value;
						}

						/**
						 * Gets the value of the petitionerAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getPetitionerAdvocate() {
							return petitionerAdvocate;
						}

						/**
						 * Sets the value of the petitionerAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setPetitionerAdvocate(Object value) {
							this.petitionerAdvocate = value;
						}

						/**
						 * Gets the value of the respondentName property.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getRespondentName() {
							return respondentName;
						}

						/**
						 * Sets the value of the respondentName property.
						 * 
						 * @param value allowed object is {@link String }
						 * 
						 */
						public void setRespondentName(String value) {
							this.respondentName = value;
						}

						/**
						 * Gets the value of the respondentAddress property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAddress() {
							return respondentAddress;
						}

						/**
						 * Sets the value of the respondentAddress property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAddress(Object value) {
							this.respondentAddress = value;
						}

						/**
						 * Gets the value of the respondentAdvocate property.
						 * 
						 * @return possible object is {@link Object }
						 * 
						 */
						public Object getRespondentAdvocate() {
							return respondentAdvocate;
						}

						/**
						 * Sets the value of the respondentAdvocate property.
						 * 
						 * @param value allowed object is {@link Object }
						 * 
						 */
						public void setRespondentAdvocate(Object value) {
							this.respondentAdvocate = value;
						}

					}

				}

			}

		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	public static class TribunalCases {

		protected Consumer consumer;

		protected Itat itat;

		protected Cestat cestat;

		protected Nse nse;

		protected Bse bse;

		protected Ipab ipab;

		protected Cic cic;

		protected Others others;

		/**
		 * Gets the value of the consumer property.
		 * 
		 * @return possible object is {@link Consumer }
		 * 
		 */
		public Consumer getConsumer() {
			return consumer;
		}

		/**
		 * Sets the value of the consumer property.
		 * 
		 * @param value allowed object is {@link Consumer }
		 * 
		 */
		public void setConsumer(Consumer value) {
			this.consumer = value;
		}

		/**
		 * Gets the value of the itat property.
		 * 
		 * @return possible object is {@link Itat }
		 * 
		 */
		public Itat getItat() {
			return itat;
		}

		/**
		 * Sets the value of the itat property.
		 * 
		 * @param value allowed object is {@link Itat }
		 * 
		 */
		public void setItat(Itat value) {
			this.itat = value;
		}

		/**
		 * Gets the value of the cestat property.
		 * 
		 * @return possible object is {@link Cestat }
		 * 
		 */
		public Cestat getCestat() {
			return cestat;
		}

		/**
		 * Sets the value of the cestat property.
		 * 
		 * @param value allowed object is {@link Cestat }
		 * 
		 */
		public void setCestat(Cestat value) {
			this.cestat = value;
		}

		/**
		 * Gets the value of the nse property.
		 * 
		 * @return possible object is {@link Nse }
		 * 
		 */
		public Nse getNse() {
			return nse;
		}

		/**
		 * Sets the value of the nse property.
		 * 
		 * @param value allowed object is {@link Nse }
		 * 
		 */
		public void setNse(Nse value) {
			this.nse = value;
		}

		/**
		 * Gets the value of the bse property.
		 * 
		 * @return possible object is {@link Bse }
		 * 
		 */
		public Bse getBse() {
			return bse;
		}

		/**
		 * Sets the value of the bse property.
		 * 
		 * @param value allowed object is {@link Bse }
		 * 
		 */
		public void setBse(Bse value) {
			this.bse = value;
		}

		/**
		 * Gets the value of the ipab property.
		 * 
		 * @return possible object is {@link Ipab }
		 * 
		 */
		public Ipab getIpab() {
			return ipab;
		}

		/**
		 * Sets the value of the ipab property.
		 * 
		 * @param value allowed object is {@link Ipab }
		 * 
		 */
		public void setIpab(Ipab value) {
			this.ipab = value;
		}

		/**
		 * Gets the value of the cic property.
		 * 
		 * @return possible object is {@link Cic }
		 * 
		 */
		public Cic getCic() {
			return cic;
		}

		/**
		 * Sets the value of the cic property.
		 * 
		 * @param value allowed object is {@link Cic }
		 * 
		 */
		public void setCic(Cic value) {
			this.cic = value;
		}

		/**
		 * Gets the value of the others property.
		 * 
		 * @return possible object is {@link Others }
		 * 
		 */
		public Others getOthers() {
			return others;
		}

		/**
		 * Sets the value of the others property.
		 * 
		 * @param value allowed object is {@link Others }
		 * 
		 */
		public void setOthers(Others value) {
			this.others = value;
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Bse {

			protected short live;

			protected short disposed;

			protected short unknown;

			protected List<Cases> cases;
			
			public List<Cases> getCases() {
				return cases;
			}

			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}

			/**
			 * Gets the value of the live property.
			 * 
			 */
			public short getLive() {
				return live;
			}

			/**
			 * Sets the value of the live property.
			 * 
			 */
			public void setLive(short value) {
				this.live = value;
			}

			/**
			 * Gets the value of the disposed property.
			 * 
			 */
			public short getDisposed() {
				return disposed;
			}

			/**
			 * Sets the value of the disposed property.
			 * 
			 */
			public void setDisposed(short value) {
				this.disposed = value;
			}

			/**
			 * Gets the value of the unknown property.
			 * 
			 */
			public short getUnknown() {
				return unknown;
			}

			/**
			 * Sets the value of the unknown property.
			 * 
			 */
			public void setUnknown(short value) {
				this.unknown = value;
			}
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					@JsonProperty(value = "case-no")

					protected Object caseNo;
					@JsonProperty(value = "case-year")

					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

					

					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Cestat {
			
			protected short live;

			protected short disposed;

			protected short unknown;

			protected List<Cases> cases;

			public short getLive() {
				return live;
			}
			public void setLive(short live) {
				this.live = live;
			}
			public short getDisposed() {
				return disposed;
			}
			public void setDisposed(short disposed) {
				this.disposed = disposed;
			}
			public short getUnknown() {
				return unknown;
			}
			public void setUnknown(short unknown) {
				this.unknown = unknown;
			}
			public List<Cases> getCases() {
				return cases;
			}
			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}

			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected Object state;

					protected Object district;

					protected Object court;
					@JsonProperty(value = "case-no")

					protected Object caseNo;
					@JsonProperty(value = "case-year")
					protected Object caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected Object petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected String petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected Object respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected String respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setState(Object value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setDistrict(Object value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCaseYear(Object value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAddress(Object value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAdvocate(String value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAddress(Object value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAdvocate(String value) {
						this.respondentAdvocate = value;
					}

				}

			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Cic {

			protected short live;

			protected short disposed;

			protected short unknown;
			
			protected List<Cases> cases;
			
			
			public List<Cases> getCases() {
				return cases;
			}

			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}

			/**
			 * Gets the value of the live property.
			 * 
			 */
			public short getLive() {
				return live;
			}

			/**
			 * Sets the value of the live property.
			 * 
			 */
			public void setLive(short value) {
				this.live = value;
			}

			/**
			 * Gets the value of the disposed property.
			 * 
			 */
			public short getDisposed() {
				return disposed;
			}

			/**
			 * Sets the value of the disposed property.
			 * 
			 */
			public void setDisposed(short value) {
				this.disposed = value;
			}

			/**
			 * Gets the value of the unknown property.
			 * 
			 */
			public short getUnknown() {
				return unknown;
			}

			/**
			 * Sets the value of the unknown property.
			 * 
			 */
			public void setUnknown(short value) {
				this.unknown = value;
			}
			
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					@JsonProperty(value = "case-no")

					protected Object caseNo;
					@JsonProperty(value = "case-year")

					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

					

					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Consumer {

			protected short live;

			protected short disposed;

			protected short unknown;
			
			protected List<Cases> cases;
			

			public List<Cases> getCases() {
				return cases;
			}

			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}

			/**
			 * Gets the value of the live property.
			 * 
			 */
			public short getLive() {
				return live;
			}

			/**
			 * Sets the value of the live property.
			 * 
			 */
			public void setLive(short value) {
				this.live = value;
			}

			/**
			 * Gets the value of the disposed property.
			 * 
			 */
			public short getDisposed() {
				return disposed;
			}

			/**
			 * Sets the value of the disposed property.
			 * 
			 */
			public void setDisposed(short value) {
				this.disposed = value;
			}

			/**
			 * Gets the value of the unknown property.
			 * 
			 */
			public short getUnknown() {
				return unknown;
			}

			/**
			 * Sets the value of the unknown property.
			 * 
			 */
			public void setUnknown(short value) {
				this.unknown = value;
			}
			
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					
					@JsonProperty(value = "case-no")
					protected Object caseNo;
					
					@JsonProperty(value = "case-year")
					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

				
					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Ipab {

			protected short live;

			protected short disposed;

			protected short unknown;
			
			protected List<Cases>cases;
			
			public List<Cases> getCases() {
				return cases;
			}

			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}

			/**
			 * Gets the value of the live property.
			 * 
			 */
			public short getLive() {
				return live;
			}

			/**
			 * Sets the value of the live property.
			 * 
			 */
			public void setLive(short value) {
				this.live = value;
			}

			/**
			 * Gets the value of the disposed property.
			 * 
			 */
			public short getDisposed() {
				return disposed;
			}

			/**
			 * Sets the value of the disposed property.
			 * 
			 */
			public void setDisposed(short value) {
				this.disposed = value;
			}

			/**
			 * Gets the value of the unknown property.
			 * 
			 */
			public short getUnknown() {
				return unknown;
			}

			/**
			 * Sets the value of the unknown property.
			 * 
			 */
			public void setUnknown(short value) {
				this.unknown = value;
			}
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					
					@JsonProperty(value = "case-no")
					protected Object caseNo;
					
					@JsonProperty(value = "case-year")

					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

					

					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Itat {

			protected short live;

			protected short disposed;

			protected short unknown;

			protected List<Cases> cases;
			
			
			public short getLive() {
				return live;
			}


			public void setLive(short live) {
				this.live = live;
			}


			public short getDisposed() {
				return disposed;
			}


			public void setDisposed(short disposed) {
				this.disposed = disposed;
			}


			public short getUnknown() {
				return unknown;
			}


			public void setUnknown(short unknown) {
				this.unknown = unknown;
			}


			public List<Cases> getCases() {
				return cases;
			}


			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}


			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					@JsonProperty(value = "case-no")

					protected Object caseNo;
					@JsonProperty(value = "case-year")

					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

				

					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Nse {

			protected short live;

			protected short disposed;

			protected short unknown;

			protected List<Cases> cases;
			
			public List<Cases> getCases() {
				return cases;
			}

			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}

			/**
			 * Gets the value of the live property.
			 * 
			 */
			public short getLive() {
				return live;
			}

			/**
			 * Sets the value of the live property.
			 * 
			 */
			public void setLive(short value) {
				this.live = value;
			}

			/**
			 * Gets the value of the disposed property.
			 * 
			 */
			public short getDisposed() {
				return disposed;
			}

			/**
			 * Sets the value of the disposed property.
			 * 
			 */
			public void setDisposed(short value) {
				this.disposed = value;
			}

			/**
			 * Gets the value of the unknown property.
			 * 
			 */
			public short getUnknown() {
				return unknown;
			}

			/**
			 * Sets the value of the unknown property.
			 * 
			 */
			public void setUnknown(short value) {
				this.unknown = value;
			}
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					@JsonProperty(value = "case-no")

					protected Object caseNo;
					@JsonProperty(value = "case-year")

					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}

					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}

		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		@JsonInclude(JsonInclude.Include.NON_DEFAULT)
		public static class Others {
			protected short live;
			protected short disposed;
			protected short unknown;
			
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			protected List<Cases>cases;
			
			public List<Cases> getCases() {
				return cases;
			}
			public void setCases(List<Cases> cases) {
				this.cases = cases;
			}
			public short getLive() {
				return live;
			}

			public void setLive(short live) {
				this.live = live;
			}

			public short getDisposed() {
				return disposed;
			}

			public void setDisposed(short disposed) {
				this.disposed = disposed;
			}

			public short getUnknown() {
				return unknown;
			}

			public void setUnknown(short unknown) {
				this.unknown = unknown;
			}
				
			@JsonIgnoreProperties(ignoreUnknown = true)
			@JsonInclude(JsonInclude.Include.NON_EMPTY)
			public static class Cases {

				protected List<Record> record;

				public List<Record> getRecord() {
					if (record == null) {
						record = new ArrayList<Record>();
					}
					return this.record;
				}

				@JsonIgnoreProperties(ignoreUnknown = true)
				@JsonInclude(JsonInclude.Include.NON_EMPTY)
				public static class Record {

					@JsonProperty(value = "sr-no")
					@JsonInclude(JsonInclude.Include.NON_DEFAULT)
					protected short srNo;

					protected String type;

					protected String state;

					protected String district;

					protected Object court;
					@JsonProperty(value = "case-no")

					protected Object caseNo;
					@JsonProperty(value = "case-year")

					protected int caseYear;
					@JsonProperty(value = "case-status")
					protected String caseStatus;
					@JsonProperty(value = "by-or-against")
					protected String byOrAgainst;

					protected Object act;
					@JsonProperty(value = "petitioner-name")
					protected String petitionerName;
					@JsonProperty(value = "petitioner-address")
					protected String petitionerAddress;
					@JsonProperty(value = "petitioner-advocate")
					protected Object petitionerAdvocate;
					@JsonProperty(value = "respondent-name")
					protected String respondentName;
					@JsonProperty(value = "respondent-address")
					protected String respondentAddress;
					@JsonProperty(value = "respondent-advocate")
					protected Object respondentAdvocate;

					/**
					 * Gets the value of the srNo property.
					 * 
					 */
					public short getSrNo() {
						return srNo;
					}

					/**
					 * Sets the value of the srNo property.
					 * 
					 */
					public void setSrNo(short value) {
						this.srNo = value;
					}

					/**
					 * Gets the value of the type property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getType() {
						return type;
					}

					/**
					 * Sets the value of the type property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setType(String value) {
						this.type = value;
					}

					/**
					 * Gets the value of the state property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getState() {
						return state;
					}

					/**
					 * Sets the value of the state property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setState(String value) {
						this.state = value;
					}

					/**
					 * Gets the value of the district property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getDistrict() {
						return district;
					}

					/**
					 * Sets the value of the district property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setDistrict(String value) {
						this.district = value;
					}

					/**
					 * Gets the value of the court property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getCourt() {
						return court;
					}

					/**
					 * Sets the value of the court property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setCourt(Object value) {
						this.court = value;
					}


					public Object getCaseNo() {
						return caseNo;
					}

					public void setCaseNo(Object caseNo) {
						this.caseNo = caseNo;
					}

					/**
					 * Gets the value of the caseYear property.
					 * 
					 */
					public int getCaseYear() {
						return caseYear;
					}

					/**
					 * Sets the value of the caseYear property.
					 * 
					 */
					public void setCaseYear(int value) {
						this.caseYear = value;
					}

					/**
					 * Gets the value of the caseStatus property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getCaseStatus() {
						return caseStatus;
					}

					/**
					 * Sets the value of the caseStatus property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setCaseStatus(String value) {
						this.caseStatus = value;
					}

					/**
					 * Gets the value of the byOrAgainst property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getByOrAgainst() {
						return byOrAgainst;
					}

					/**
					 * Sets the value of the byOrAgainst property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setByOrAgainst(String value) {
						this.byOrAgainst = value;
					}

					/**
					 * Gets the value of the act property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getAct() {
						return act;
					}

					/**
					 * Sets the value of the act property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setAct(Object value) {
						this.act = value;
					}

					/**
					 * Gets the value of the petitionerName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerName() {
						return petitionerName;
					}

					/**
					 * Sets the value of the petitionerName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerName(String value) {
						this.petitionerName = value;
					}

					/**
					 * Gets the value of the petitionerAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getPetitionerAddress() {
						return petitionerAddress;
					}

					/**
					 * Sets the value of the petitionerAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setPetitionerAddress(String value) {
						this.petitionerAddress = value;
					}

					/**
					 * Gets the value of the petitionerAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getPetitionerAdvocate() {
						return petitionerAdvocate;
					}

					/**
					 * Sets the value of the petitionerAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setPetitionerAdvocate(Object value) {
						this.petitionerAdvocate = value;
					}

					/**
					 * Gets the value of the respondentName property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentName() {
						return respondentName;
					}

					/**
					 * Sets the value of the respondentName property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentName(String value) {
						this.respondentName = value;
					}

					/**
					 * Gets the value of the respondentAddress property.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getRespondentAddress() {
						return respondentAddress;
					}

					/**
					 * Sets the value of the respondentAddress property.
					 * 
					 * @param value allowed object is {@link String }
					 * 
					 */
					public void setRespondentAddress(String value) {
						this.respondentAddress = value;
					}

					/**
					 * Gets the value of the respondentAdvocate property.
					 * 
					 * @return possible object is {@link Object }
					 * 
					 */
					public Object getRespondentAdvocate() {
						return respondentAdvocate;
					}

					/**
					 * Sets the value of the respondentAdvocate property.
					 * 
					 * @param value allowed object is {@link Object }
					 * 
					 */
					public void setRespondentAdvocate(Object value) {
						this.respondentAdvocate = value;
					}

				}

			}
		 

		}

	}

	@Override
	public String toString() {
		return "ReportToJson [title=" + title + ", requestDetails=" + requestDetails + ", summary=" + summary
				+ ", supremeCourt=" + supremeCourt + ", highCourt=" + highCourt + ", districtCourt=" + districtCourt
				+ ", highRiskCourt=" + highRiskCourt + ", tribunalCases=" + tribunalCases + ", defaulterCases="
				+ defaulterCases + "]";
	}

}
