<script>
function getHint(){
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                         var p=xmlhttp.responseText;
		if(p!="")
	                document.getElementById("findborrower").value=p;
            }
        };
        xmlhttp.open("GET", "/cgi-bin/koha/authorities/search_user_tag.pl", true);
        xmlhttp.send();

}

</script>
[% IF ( CAN_user_circulate_circulate_remaining_permissions ) %]
    <div id="circ_search" class="residentsearch">
    <p class="tip">Enter patron card number or partial name:</p>
    <form action="/cgi-bin/koha/circ/circulation.pl" id="patronsearch" method="post">
    [% IF ( CircAutocompl ) %]
    <div class="autocomplete">
                <input autocomplete="off" id="findborrower" name="findborrower" size="40" class="head-searchbox focus" type="text" onfocus="getHint()"/>
                <input id="autocsubmit" type="submit" class="submit" value="Submit" />
            [% IF ( stickyduedate ) %]
                <input type="hidden" name="duedatespec" value="[% duedatespec %]" />
                <input type="hidden" name="stickyduedate" value="[% stickyduedate %]" />
            [% END %]
        </div>
	[% ELSE %]
            <input id="findborrower" name="findborrower" size="40" class="head-searchbox focus" type="text" onfocus="getHint()" />
            [% IF ( stickyduedate ) %]
            <input type="hidden" name="duedatespec" value="[% duedatespec %]" />
            <input type="hidden" name="stickyduedate" value="[% stickyduedate %]" />
            [% END %]
            <input value="Submit" class="submit" type="submit" />
	[% END %]
    </form>
	</div>
	[% END %]
