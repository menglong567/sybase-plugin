package net.sourceforge.squirrel_sql.plugins.SybaseASE.tab;
import net.sourceforge.squirrel_sql.client.session.mainpanel.objecttree.tabs.FormattedSourceTab;
import net.sourceforge.squirrel_sql.fw.sql.IDatabaseObjectInfo;
/**
 * This class provides the necessary information to the parent tab to display the source for a Sybase trigger.
 */
public class TriggerSourceTab extends FormattedSourceTab
{
    /**
     * Constructor
     *
     * @param hint
     *        what the user sees on mouse-over tool-tip
     * @param stmtSep
     *        the string to use to separate SQL statements
     */
    public TriggerSourceTab(String hint, String stmtSep) {
        super(hint);
        super.setCompressWhitespace(true);
        super.setupFormatter(stmtSep, null);
    }

    @Override
    protected String getSqlStatement()
    {
        return
                "SELECT trigger_defs.text " +
                        "FROM sysobjects tables , sysobjects triggers, syscomments trigger_defs " +
                        "where triggers.type = 'TR' " +
                        "and triggers.id = trigger_defs.id " +
                        "and triggers.deltrig = tables.id " +
                        "and tables.loginame = ? " +
                        // TODO: figure out how to get the name of the table that the trigger
                        //       is on.
                        //"and tables.name = ? " +
                        "and triggers.name = ? ";
    }

    @Override
    protected String[] getBindValues()
    {
        final IDatabaseObjectInfo doi = getDatabaseObjectInfo();
        return new String[] { doi.getCatalogName(), doi.getSimpleName() };
    }
}