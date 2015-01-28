package yale.pathology.model;

/**
 * Created by pg86 on 1/19/15.
 */
public class Publication
{
    //{"id":"23372985",
    // "source":"MED",
    // "pmid":"23372985",
    // "pmcid":"PMC3551490",
    // "title":"Custom software development for use in a clinical laboratory.",
    // "authorString":"Sinard JH, Gershkovich P.",
    // "journalTitle":"J Pathol Inform",
    // "journalVolume":"3",
    // "pubYear":"2012",

    // "journalIssn":"2229-5089",
    // "pageInfo":"44",
    // "pubType":"journal article",
    // "isOpenAccess":"Y",
    // "inEPMC":"Y",
    // "inPMC":"Y",
    // "citedByCount":2,
    // "hasReferences":"N",
    // "hasTextMinedTerms":"Y",
    // "hasDbCrossReferences":"N",
    // "hasLabsLinks":"N",
    // "hasTMAccessionNumbers":"N",
    // "luceneScore":"1186.1707",
    // "doi":"10.4103/2153-3539.104906"}


    private String id;
    private String source;

    private String pmid;
    private String pmcid;
    private String title;
    private String authorString;
    private String journalTitle;
    private String journalVolume;
    private String pubYear;
    private String journalIssn;
    private String pageInfo;
    private String pubType;
    private String isOpenAccess;
    private String inEPMC;
    private String inPMC;
    private int citedByCount;
    private String hasReferences;
    private String hasTextMinedTerms;
    private String hasDbCrossReferences;
    private String hasLabsLinks;
    private String hasTMAccessionNumbers;
    private String luceneScore;
    private String doi;


    public String getId()
    {

        return id;
    }

    public void setId(String id)
    {

        this.id = id;
    }

    public String getSource()
    {

        return source;
    }

    public void setSource(String source)
    {

        this.source = source;
    }

    public String getPmid()
    {

        return pmid;
    }

    public void setPmid(String pmid)
    {

        this.pmid = pmid;
    }

    public String getPmcid()
    {

        return pmcid;
    }

    public void setPmcid(String pmcid)
    {

        this.pmcid = pmcid;
    }

    public String getTitle()
    {

        return title;
    }

    public void setTitle(String title)
    {

        this.title = title;
    }

    public String getAuthorString()
    {

        return authorString;
    }

    public void setAuthorString(String authorString)
    {

        this.authorString = authorString;
    }

    public String getJournalTitle()
    {

        return journalTitle;
    }

    public void setJournalTitle(String journalTitle)
    {

        this.journalTitle = journalTitle;
    }

    public String getJournalVolume()
    {

        return journalVolume;
    }

    public void setJournalVolume(String journalVolume)
    {

        this.journalVolume = journalVolume;
    }

    public String getPubYear()
    {

        return pubYear;
    }

    public void setPubYear(String pubYear)
    {

        this.pubYear = pubYear;
    }

    public String getJournalIssn()
    {

        return journalIssn;
    }

    public void setJournalIssn(String journalIssn)
    {

        this.journalIssn = journalIssn;
    }

    public String getPageInfo()
    {

        return pageInfo;
    }

    public void setPageInfo(String pageInfo)
    {

        this.pageInfo = pageInfo;
    }

    public String getPubType()
    {

        return pubType;
    }

    public void setPubType(String pubType)
    {

        this.pubType = pubType;
    }

    public String getIsOpenAccess()
    {

        return isOpenAccess;
    }

    public void setIsOpenAccess(String isOpenAccess)
    {

        this.isOpenAccess = isOpenAccess;
    }

    public String getInEPMC()
    {

        return inEPMC;
    }

    public void setInEPMC(String inEPMC)
    {

        this.inEPMC = inEPMC;
    }

    public String getInPMC()
    {

        return inPMC;
    }

    public void setInPMC(String inPMC)
    {

        this.inPMC = inPMC;
    }

    public int getCitedByCount()
    {
        return citedByCount;
    }

    public void setCitedByCount(int citedByCount)
    {
        this.citedByCount = citedByCount;
    }

    public String getHasReferences()
    {

        return hasReferences;
    }

    public void setHasReferences(String hasReferences)
    {

        this.hasReferences = hasReferences;
    }

    public String getHasTextMinedTerms()
    {

        return hasTextMinedTerms;
    }

    public void setHasTextMinedTerms(String hasTextMinedTerms)
    {

        this.hasTextMinedTerms = hasTextMinedTerms;
    }

    public String getHasDbCrossReferences()
    {

        return hasDbCrossReferences;
    }

    public void setHasDbCrossReferences(String hasDbCrossReferences)
    {

        this.hasDbCrossReferences = hasDbCrossReferences;
    }

    public String getHasLabsLinks()
    {

        return hasLabsLinks;
    }

    public void setHasLabsLinks(String hasLabsLinks)
    {

        this.hasLabsLinks = hasLabsLinks;
    }

    public String getHasTMAccessionNumbers()
    {

        return hasTMAccessionNumbers;
    }

    public void setHasTMAccessionNumbers(String hasTMAccessionNumbers)
    {

        this.hasTMAccessionNumbers = hasTMAccessionNumbers;
    }

    public String getLuceneScore()
    {

        return luceneScore;
    }

    public void setLuceneScore(String luceneScore)
    {

        this.luceneScore = luceneScore;
    }

    public String getDoi()
    {

        return doi;
    }

    public void setDoi(String doi)
    {

        this.doi = doi;
    }

    /**
     * Example
     * [1] Sinard JH, Gershkovich P: Custom software development for use in a clinical laboratory. Journal of pathology informatics 2012, 3:44.
     * //for direct display look up use http://www.ncbi.nlm.nih.gov/pubmed/?term=PMC2244333
     * @return
     */

    public String toString()
    {
        return String.format(" %s: %s %s %s, %s:%s (%s)", getAuthorString(),  getTitle(), getJournalTitle(), getPubYear(), getJournalVolume(), getPageInfo(), getPmid());

    }
}
