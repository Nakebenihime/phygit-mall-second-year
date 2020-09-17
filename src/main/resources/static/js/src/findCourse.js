jQuery(document).ready(function(){
    $('#dispoFormB').on('click', function (e) {
        e.preventDefault(); // disable the default form submit event
        if ($('#coursePref').find('#durationPref').length || $('#inputDuration').val() == '') {
            // found!
        }else{
            $('#coursePref').append("<div class='card text-right' style='width: 18rem;'>\n" +
                "  <div id='durationPref' class='card-body'>\n" +
                "    <h5 class='card-title'>Durée</h5>\n" +
                "    <p data-duration="+$('#inputDuration').val()+" class='card-text'>"+$('#inputDuration').val()+" minutes</p>\n" +
                "    <a href='#' class='btn btn-danger prefButton'>Supprimer</a>\n" +
                "  </div>\n" +
                "</div>")
        }

    });
    
    $('#dateFormB').on('click', function (e) {
        e.preventDefault(); // disable the default form submit event
       
            $('#coursePref').append("<div class='card text-right' style='width: 18rem;'>\n" +
                "  <div id='datePref' class='card-body'>\n" +
                "    <h5 class='card-title'>Date</h5>\n" +
                "    <p data-date="+$('#inputDate').val()+" class='card-text'>"+$('#inputDate').val()+" </p>\n" +
                "    <p data-heure="+$('#inputHeure').val()+" class='card-text'>"+$('#inputHeure').val()+" </p>\n" +
                "    <a href='#' class='btn btn-danger prefButton'>Supprimer</a>\n" +
                "  </div>\n" +
                "</div>")
        

    });

    $('#storeFormB').on('click', function (e) {
        e.preventDefault(); // disable the default form submit event
        nbStore = $('#coursePref').find('.storePref').length
        nbStoreLength = nbStore;
        if ( $('#inputCinema').is(":checked")) {
        	nbStoreLength = nbStoreLength - 1;
        }
        if ($('#inputRestaurant').is(":checked")) {
        	nbStoreLength = nbStoreLength -1;
        }

        if (nbStoreLength +1 >  $('#inputDuration').val()/20) {
        	  $('#coursePref').append("<div class='card text-right storePref' style='width: 18rem;'>\n" +
      	            "  <div class='card-body'>\n" +
      	            "    <h5 class='card-title'>Magasin"+(nbStore+1)+" : L'ajout de cette boutique depasse le temps dont vous disposez</h5>\n" +
      	            "    <p data-store="+$("#inputStore").val()+" class='card-text'>"+$("#inputStore option:selected").text()+"</p>\n" +
      	            "    <a href='#' class='btn btn-danger prefButton'>Supprimer</a>\n" +
      	            "  </div>\n" +
      	            "</div>")
        }
        else {
	        $('#coursePref').append("<div class='card text-right storePref' style='width: 18rem;'>\n" +
	            "  <div class='card-body'>\n" +
	            "    <h5 class='card-title'>Magasin"+(nbStore+1)+"</h5>\n" +
	            "    <p data-store="+$("#inputStore").val()+" class='card-text'>"+$("#inputStore option:selected").text()+"</p>\n" +
	            "    <a href='#' class='btn btn-danger prefButton'>Supprimer</a>\n" +
	            "  </div>\n" +
	            "</div>")
        }
//           if (nbStore > $('#inputDuration').val()/20) {
//        	   alert("OUII PASASSEZ EDZ TEMPS");
//           }
    });

   $('#optionFormB').on('click', function (e) {
       e.preventDefault(); // disable the default form submit event
       var cinema = $('#inputCinema').is(":checked");
       var restaurant = $('#inputRestaurant').is(":checked");
       var cText = "";
       var cRestaurant = "";
       if(cinema){cText = "Cinéma"};
       if(restaurant){cRestaurant = "Restaurant"};
       if ($('#coursePref').find('#optionPref').length || (cinema==false && restaurant==false)) {
           // found!
       }else {
           $('#coursePref').append("<div class='card text-right storePref' style='width: 18rem;'>\n" +
               "  <div id='optionPref' class='card-body'>\n" +
               "    <h5 class='card-title'>Options du parcours (Attention, cette option n'est pas pris en compte dans la durée dont vous disposez)</h5>\n" +
               "    <p id='cinemaPref' data-cinema=" + cinema + " class='card-text'>" + cText + "</p>\n" +
               "    <p id='restaurantPref' data-restaurant=" + restaurant + " class='card-text'>" + cRestaurant + "</p>\n" +
               "    <a href='#' class='btn btn-danger prefButton'>Supprimer</a>\n" +
               "  </div>\n" +
               "</div>")

       }
   });

    $("#coursePref").on("click", ".prefButton", function(e) {
        $(this).parent().parent().remove();
    });



    $("#computeCourse").on("click", function(e) {
        var duration;
        duration = $("#durationPref").find("p").data("duration");
        var cinema;
        var restaurant;
        cinema = $("#cinemaPref").data("cinema");
        restaurant = $("#restaurantPref").data("restaurant");
        var date;
        var heure;
        date = $("#datePref").find("p").data("date");
        heure = $("#datePref").find("p").data("heure");
        console.log(cinema+"---"+restaurant);
        var stores = new Array();
        $('[data-store]').each(function() {
            stores.push($(this).data("store"));
        });
        $.ajax({
            url: '/course',
            dataType: 'json',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify( { "duration": duration,"stores":stores.join(','),"cinema": cinema,"restaurant": restaurant ,"date":date, "heure":heure}),
            processData: false,
            success: function( data, textStatus, jQxhr ){
//                $("#searchResult").find('*').not('#plan').remove();
                $("#accordionForm").addClass("invisible");
                $("#searchResult").removeClass("invisible");
                var addText = "<button id=\"newForm\" class=\"btn btn-success visible\">Nouvelle Recherche</button>";
                var time = 0;


                $.each(data, function(idx, obj) {
                	$("#"+obj.id+"").css('background', '#007bff');



//                	 $("#searchResult").getElementById(obj.store).style.backgroundColor='black';

                		var text = "<p><h2>" + obj.type + ": "+obj.store
	                        +"</h2> Empl : "+obj.num
 	                        + ", Aile : "+obj.wing
 	                        +" , Etage : "+obj.floor
 	                        +", Temps : " + obj.time + " minutes"
 	                        +", Date : " + obj.datetime
 	                        +"</p>";
                		document.getElementById("searchResult").innerHTML += text;

//                		 $("#searchResult").add("<p>" + obj.type + ": "+obj.store
//     	                        +" Empl : "+obj.num
//     	                        + ", Aile : "+obj.wing
//     	                        +" , Etage : "+obj.floor
//     	                        +", Temps : " + obj.time + " minutes"
//     	                        +", Date : " + obj.datetime
//     	                        +"</p>");



                });
                document.getElementById("searchResult").innerHTML += addText;


            },
            error: function( jqXhr, textStatus, errorThrown ){
                console.log( errorThrown );
            }
        });
    });

    $('#searchResult').on('click','#newForm' ,function (e) {
        $("#searchResult").removeClass("visible");
        $("#searchResult").addClass("invisible");
        $("#accordionForm").removeClass("invisible");
        $("#accordionForm").addClass("visible");
        $("#searchResult").empty();
        $("#coursePref").empty();

    });

});