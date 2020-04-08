package net.sourceforge.squirrel_sql.plugins.SybaseASE.prefs;

import java.io.Serializable;
import net.sourceforge.squirrel_sql.fw.preferences.IQueryTokenizerPreferenceBean;

/**
 * A bean class to store preferences for the Sybase plugin.
 * @author mengl
 */
public class SybasePreferenceBean implements Cloneable, Serializable, IQueryTokenizerPreferenceBean {
    static final long serialVersionUID = 3722068008392095286L;
    static final String UNSUPPORTED = "Unsupported";
    /**
     * Client Name.
     */
    private String _clientName;

    /**
     * Client version.
     */
    private String _clientVersion;
    private String statementSeparator = "GO";
    private String procedureSeparator = "GO";
    private String lineComment = "--";
    private boolean removeMultiLineComments = false;
    private boolean installCustomQueryTokenizer = true;

    public SybasePreferenceBean() {
        super();
    }

    /**
     * Return a copy of this object.
     */
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.getMessage()); // Impossible.
        }
    }

    /**
     * Retrieve the client to use. This is only
     * used if <TT>useAnonymousClient</TT> is false.
     *
     * @return Client name.
     */
    @Override
    public String getClientName() {
        return _clientName;
    }

    /**
     * Set the client name.
     *
     * @param value Client name
     */
    @Override
    public void setClientName(String value) {
        _clientName = value;
    }

    /**
     * Retrieve the client version to use. This is only
     * used if <TT>useAnonymousLogon</TT> is false.
     *
     * @return Client version.
     */
    @Override
    public String getClientVersion() {
        return _clientVersion;
    }

    /**
     * Set the client version.
     *
     * @param value Client version
     */
    @Override
    public void setClientVersion(String value) {
        _clientVersion = value;
    }

    /**
     * @param statementSeparator the statementSeparator to set
     */
    @Override
    public void setStatementSeparator(String statementSeparator) {
        this.statementSeparator = statementSeparator;
    }

    /**
     * @return the statementSeparator
     */
    @Override
    public String getStatementSeparator() {
        return statementSeparator;
    }

    /**
     * @param lineComment the lineComment to set
     */
    @Override
    public void setLineComment(String lineComment) {
        this.lineComment = lineComment;
    }

    /**
     * @return the lineComment
     */
    @Override
    public String getLineComment() {
        return lineComment;
    }

    /**
     * @param removeMultiLineComments the removeMultiLineComments to set
     */
    @Override
    public void setRemoveMultiLineComments(boolean removeMultiLineComments) {
        this.removeMultiLineComments = removeMultiLineComments;
    }

    /**
     * @return the removeMultiLineComments
     */
    @Override
    public boolean isRemoveMultiLineComments() {
        return removeMultiLineComments;
    }

    /**
     * @param installCustomQueryTokenizer the installCustomQueryTokenizer to set
     */
    @Override
    public void setInstallCustomQueryTokenizer(boolean installCustomQueryTokenizer) {
        this.installCustomQueryTokenizer = installCustomQueryTokenizer;
    }

    /**
     * @return the installCustomQueryTokenizer
     */
    @Override
    public boolean isInstallCustomQueryTokenizer() {
        return installCustomQueryTokenizer;
    }

    /**
     * @see net.sourceforge.squirrel_sql.fw.preferences.IQueryTokenizerPreferenceBean#getProcedureSeparator()
     */
    @Override
    public String getProcedureSeparator() {
        return procedureSeparator;
    }

    /**
     * @see net.sourceforge.squirrel_sql.fw.preferences.IQueryTokenizerPreferenceBean#setProcedureSeparator(java.lang.String)
     */
    @Override
    public void setProcedureSeparator(String procedureSeparator) {
        this.procedureSeparator = procedureSeparator;
    }
}