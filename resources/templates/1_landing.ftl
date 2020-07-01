<html>
<head>
    <#include "/common/head.ftl">
    <title>Lexencrypt</title>
</head>
<body>
<div class="content">
    <p>
    Great work! You remind me of another talented hacker.
    </p>
    <img src="/static/images/donatello_typing.gif" />
    <p>
    Now that the evidence has been destroyed, the feds won't have a case
    against him, and he can safely return to America.
    </p>
    <p>
    Chris needs some important information as he re-enters the country.
    The package he sent you includes flight paths and radar zones, rendered
    with colored lines and circles. What colors did he use?
    </p>
    <#if error??>
        <p style="color:red;">${error}</p>
    </#if>
    <#-- TODO: Rewrite action to root. -->
    <form action="/landing" method="post" enctype="application/x-www-form-urlencoded">
        <input type="checkbox" id="color1" name="color" value="red">
        <label for="color1"> Red</label><br>
        <input type="checkbox" id="color2" name="color" value="orange">
        <label for="color2"> Orange</label><br>
        <input type="checkbox" id="color3" name="color" value="yellow">
        <label for="color3"> Yellow</label><br>
        <input type="checkbox" id="color4" name="color" value="green">
        <label for="color4"> Green</label><br>
        <input type="checkbox" id="color5" name="color" value="blue">
        <label for="color5"> Blue</label><br>
        <input type="checkbox" id="color6" name="color" value="purple">
        <label for="color6"> Purple</label><br>
        <input type="checkbox" id="color7" name="color" value="brown">
        <label for="color7"> Brown</label><br>
        <input type="checkbox" id="color8" name="color" value="black">
        <label for="color8"> Black</label><br>

        <div class="col">
            <label for="test" style="display: inline-block;"> Actually, it was: </label>
            <input type="text" style="width:70%" name="answer" /></div>
        <div class="col"><input type="submit" value="Submit" /></div>
    </form>
</div>
</body>
</html>