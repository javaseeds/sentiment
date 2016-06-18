<#-- @ftlvariable name="" type="funk.shane.views.IndexView" -->
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" type="text/css" href="/assets/app.css">
    </head>

    <body>
        <h1>Tweet Sentiment Analysis (v0.0.6)</h1>
        <div>Taking a look at most recent Tweets</div>
        <p />
        <div>
          <form action="/" method="POST">
            <label for="queryTerm">Find Tweets by Hashtag</label>
            <input type="input" name="queryTerm" id="queryTerm" size="20" />

            <input type="submit" value="Find Tweets" />
          </form>
        </div>
        <p />
        <#if tweets.statuses?has_content>
            <table cellspacing="0" cellpadding="0" border="1">
                <tr>
                  <th style="width: 20%">Created</th>
                  <th style="width: 40%">Text</th>
                  <th style="width: 15%">Location</th>
                  <th style="width: 15%">User</th>
                  <th style="width: 10%">Score</th>
                </tr>
                <#list tweets.statuses as status>
                  <tr>
                    <td>${status.created_at}</td>
                    <td>${status.text}</td>
                    <td>${status.user.location}</td>
                    <td>${status.user.name}</td>
                    <#if (status.score < 0)>
                       <td class="negativeScore">${status.score}</td>
                    <#else>
                       <td class="positiveScore">${status.score}</td>
                    </#if>
                  </tr>
                </#list>
            </table>
    </#if>
    </body>
</html>