package com.opl.mudra.api.enums.oneform;

/**
 * @author Sanket
 */
public enum TypeOfOwnership {
    /*Type of Ownership
    Private Limited	Public Ltd-Listed	Public Ltd-Unlisted	Partnership	Sole Proprietorship	Limited Liability Partnership	Hindu Undivided Family-HUF	Government Entity	Foreign Company/Subsidiary of a Foreign Company	One Person Company*/
    PRIVATELIMITED(1, "Private Limited", "Private Limited"),
    PUBLICLTDLISTED(2, "Public Ltd-Listed", "Public Ltd-Listed"),
    PUBLICLTDUNLOSTED(3, "Public Ltd-Unlisted", "Public Ltd-Unlisted"),
    PARTNERSHIP(4, "Partnership", "Partnership"),
    SOLEPROPRIETORSHIP(5, "Sole Proprietorship", "Sole Proprietorship"),
    LIMITEDLIABLITYPARTNERSHIP(6, "Limited Liability Partnership", "Limited Liability Partnership"),
    HUF(7, "Hindu Undivided Family-HUF", "Hindu Undivided Family-HUF"),
    GIVERNMENTENTITY(8, "Government Entity", "Government Entity"),
    FOREIGNCOMPANY(9, "Foreign Company/Subsidiary of a Foreign Company", "Foreign Company/Subsidiary of a Foreign Company"),
    ONEPERSONCOMPANY(10, "One Person Company", "One Person Company");

    private final Integer id;
    private final String value;
    private final String description;

    TypeOfOwnership(Integer id, String value, String description) {
        this.id = id;
        this.value = value;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public static TypeOfOwnership getById(Integer id) {
        switch (id) {
            case 1:
                return PRIVATELIMITED;
            case 2:
                return PUBLICLTDLISTED;
            case 3:
                return PUBLICLTDUNLOSTED;
            case 4:
                return PARTNERSHIP;
            case 5:
                return SOLEPROPRIETORSHIP;
            case 6:
                return LIMITEDLIABLITYPARTNERSHIP;
            case 7:
                return HUF;
            case 8:
                return GIVERNMENTENTITY;
            case 9:
                return FOREIGNCOMPANY;
            case 10:
                return ONEPERSONCOMPANY;
            default:
                return null;
        }
    }

    public static TypeOfOwnership[] getAll() {
        return TypeOfOwnership.values();

    }
}