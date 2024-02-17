package org.jenkinsci.plugins.gitclient;

import java.io.File;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class JGitAPIImplTest extends GitAPITestUpdate {
    @Override
    protected GitClient setupGitAPI(File ws) throws Exception {
        GitClient client = Git.with(listener, env).in(ws).using("jgit").getClient();
        client.config(GitClient.ConfigLevel.LOCAL, "commit.gpgsign", "false");
        client.config(GitClient.ConfigLevel.LOCAL, "tag.gpgSign", "false");
        return client;
    }

    @Override
    protected boolean hasWorkingGetRemoteSymbolicReferences() {
        return true; // JGit 5.10 has getRemoteSymbolicReferences, prior did not
    }

    @Override
    protected boolean getTimeoutVisibleInCurrentTest() {
        return true; // git client plugin 3.11.0 supports JGit timeout
    }

    @Override
    protected String getRemoteBranchPrefix() {
        return "";
    }
}
