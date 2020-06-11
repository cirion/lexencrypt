<html>
<head>
    <#include "/common/head.ftl">
    <title>🏠️</title>
</head>
<body>
<div class="content">
    <p>
    All of this fuss over a few Ninja Turtles! Let’s be honest: they just made the Ninja Turtles to sell toys.
    </p>
    <p>
    Wait! That reminds me…
    </p>
    <p>
    Chris was talking about a movie from the 1980s that was about… a video game tournament or something? That movie includes a ridiculous scene which only exists to show off a brand new accessory for a video game console. In that scene, someone boasts about how many games they own.
    </p>
    <p>
    How many games is it?
    </p>
    <#if error??>
        <p style="color:red;">${error}</p>
    </#if>
    <form action="/powerglove" method="post" enctype="application/x-www-form-urlencoded">
        <div class="col"><input type="text" name="answer" /></div>
        <div class="col"><input type="submit" value="Submit" /></div>
    </form>
</div>
</body>
</html>