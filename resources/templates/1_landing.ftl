<html>
<head>
    <#include "/common/head.ftl">
    <title>Lexencrypt</title>
</head>
<body>
<div class="content">
    <p>
    Great work! That should get the feds off of Chris's back.
    </p>
    <p>
    Chris needs an important piece of information. The package he sent you should include a picture of a tree. How many
    roots does that tree have?
    </p>
    <#if error??>
        <p style="color:red;">${error}</p>
    </#if>
    <#-- TODO: Rewrite action to root. -->
    <form action="/landing" method="post" enctype="application/x-www-form-urlencoded">
        <div class="col"><input type="text" name="answer" /></div>
        <div class="col"><input type="submit" value="Submit" /></div>
    </form>
</div>
</body>
</html>