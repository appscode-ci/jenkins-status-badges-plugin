package org.jenkinsci.plugins.statusbadges;

import hudson.model.AbstractProject;
import hudson.model.*;
import hudson.plugins.cobertura.CoberturaBuildAction;
import hudson.plugins.cobertura.targets.CoverageMetric;

public class CoverageStatus
    extends BuildStatus
{

    public int getCoverage( AbstractProject<?, ?> project )
    {
        AbstractBuild<?, ?> lastBuild = project.getLastBuild();
        try
        {
            CoberturaBuildAction coberturaAction = lastBuild.getAction( hudson.plugins.cobertura.CoberturaBuildAction.class );
            return coberturaAction.getResult().getCoverage( CoverageMetric.LINE ).getPercentage();
        }
        catch ( Exception coberturaE )
        {
            return -1;
        }
    }

}
