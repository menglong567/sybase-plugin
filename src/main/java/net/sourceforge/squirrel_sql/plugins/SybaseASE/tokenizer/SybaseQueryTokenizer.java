package net.sourceforge.squirrel_sql.plugins.SybaseASE.tokenizer;

import net.sourceforge.squirrel_sql.fw.preferences.IQueryTokenizerPreferenceBean;
import net.sourceforge.squirrel_sql.fw.sql.IQueryTokenizer;
import net.sourceforge.squirrel_sql.fw.sql.ITokenizerFactory;
import net.sourceforge.squirrel_sql.fw.sql.QueryTokenizer;
import net.sourceforge.squirrel_sql.fw.sql.TokenizerSessPropsInteractions;
import net.sourceforge.squirrel_sql.fw.util.log.ILogger;
import net.sourceforge.squirrel_sql.fw.util.log.LoggerController;

/**
 *
 * At the moment, the only purpose this serves is to provide another bucket for
 * configuration, allowing Sybase plugin to store separator preferences separately
 * from other plugins. This will be expanded to handle stored procedures at a
 * later time.
 *
 * @author manningr
 */
public class SybaseQueryTokenizer extends QueryTokenizer implements IQueryTokenizer
{
    /** Logger for this class. */
    @SuppressWarnings("unused")
    private final static ILogger s_log =
            LoggerController.createLogger(SybaseQueryTokenizer.class);

    /** the preference bean */
    private IQueryTokenizerPreferenceBean _prefs = null;

    public SybaseQueryTokenizer(IQueryTokenizerPreferenceBean prefs)
    {
        super(prefs);
        _prefs = prefs;
    }

    @Override
    public void setScriptToTokenize(String script) {
        super.setScriptToTokenize(script);
        _queryIterator = _queries.iterator();
    }

    /**
     * Sets the ITokenizerFactory which is used to create additional instances
     * of the IQueryTokenizer - this is used for handling file includes
     * recursively.
     */
    @Override
    protected void setFactory() {
        _tokenizerFactory = new ITokenizerFactory() {
            @Override
            public IQueryTokenizer getTokenizer() {
                return new SybaseQueryTokenizer(_prefs);
            }
        };
    }

    @Override
    public TokenizerSessPropsInteractions getTokenizerSessPropsInteractions()
    {
        if(_prefs.isInstallCustomQueryTokenizer())
        {
            TokenizerSessPropsInteractions ret = new TokenizerSessPropsInteractions();
            ret.setTokenizerDefinesRemoveMultiLineComment(true);
            ret.setTokenizerDefinesStartOfLineComment(true);
            ret.setTokenizerDefinesStatementSeparator(true);

            return ret;
        }
        else
        {
            return super.getTokenizerSessPropsInteractions();
        }
    }
}