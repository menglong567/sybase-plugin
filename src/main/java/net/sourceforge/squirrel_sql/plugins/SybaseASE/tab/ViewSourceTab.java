package net.sourceforge.squirrel_sql.plugins.SybaseASE.tab;

import net.sourceforge.squirrel_sql.client.session.mainpanel.objecttree.tabs.FormattedSourceTab;
import net.sourceforge.squirrel_sql.fw.sql.IDatabaseObjectInfo;

/**
 * This class provides the necessary information to the parent tab to display the source for an Sybase view.
 */
public class ViewSourceTab extends FormattedSourceTab {
    /**
     * Constructor
     *
     * @param hint    what the user sees on mouse-over tool-tip
     * @param stmtSep the string to use to separate SQL statements
     */
    public ViewSourceTab(String hint, String stmtSep) {
        super(hint);
        super.setCompressWhitespace(false);
        super.setupFormatter(stmtSep, null);
    }

    @Override
    protected String getSqlStatement() {
        return
                "select text " +
                        "from sysobjects " +
                        "inner join syscomments on syscomments.id = sysobjects.id " +
                        "where loginame = ? " +
                        "and name = ? ";
    }

    @Override
    protected String[] getBindValues() {
        final IDatabaseObjectInfo doi = getDatabaseObjectInfo();
        return new String[]{doi.getCatalogName(), doi.getSimpleName()};
    }
}