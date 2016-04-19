// Initialize your app
var myApp = new Framework7();

// Export selectors engine
var $$ = Dom7;

// Add view
var mainView = myApp.addView('.view-main', {
    // Because we use fixed-through navbar we can enable dynamic navbar
    dynamicNavbar: true
});

// Callbacks to run specific code for specific pages, for example for About page:
myApp.onPageInit('about', function (page) {
	$$('.create-page').on('click', function () {
        createContentPage();
    });
});

myApp.onPageInit('form', function (page) {
	$$("#cancelButton").on('click', function(){
		navigator.router.back();
	});
	
	$$(".submit-button").on('click', function () {
		
		var name 	= $$('.name-field').val();
		var email 	= $$('.email-field').val();
		var url 	= $$('.url-field').val();
		var mobile 	= $$('.mobile-field').val();
		var work 	= $$('.work-field').val();
		var home 	= $$('.home-field').val();
		var picture = $$('.picture-field').val();
		
		//New Contact
		var nC = navigator.contacts.create();
		nC.name = name;
		nC.displayName = name;
		
		var emails = [];
		emails[0] = new ContactField('email', email, true);
		nC.emails = emails;
		
		var urls = [];
		urls[0] = new ContactField('url', url, true);
		nC.urls = urls;
		
		var phoneNumbers = [];
		phoneNumbers[0] = new ContactField('work', work, false);
		phoneNumbers[1] = new ContactField('mobile', mobile, true);
		phoneNumbers[2] = new ContactField('home', home, false);
		nC.phoneNumbers = phoneNumbers;

		var photos = [];
		photos[0] =  new ContactField('photo', picture, true);
		nC.photos = photos;
		
		
		//Save
		nC.save(onContactSaveSuccess, onContactSaveFail);

		
		 
	
	});
	
	function onContactSaveSuccess(data){
		alert("Saved new contact");
	}
	
	function onContactSaveFail(){
		alert("Failed to save contact");
	}
	
	function onImagePickSuccess(imageData){
		alert('Successly picked image')
		$$('#imageData').val(imageData);
	}
		
		

	function onImagePickFail(message){
		alert('Fail')
		
	}
	
	$$('#getImage').on('click', function(){
		navigator.camera.getPicture(onImagePickSuccess, onImagePickFail,
		{
			sourceType:navigator.camera.PictureSourceType.PHOTOLIBRARY,
			destinationType:navigator.camera.DestinationType.FILE_URI,
		})
	})

});





	 
// Generate dynamic page
var dynamicPageIndex = 0;
function createContentPage() {
	mainView.router.loadContent(
        '<!-- Top Navbar-->' +
        '<div class="navbar">' +
        '  <div class="navbar-inner">' +
        '    <div class="left"><a href="#" class="back link"><i class="icon icon-back"></i><span>Back</span></a></div>' +
        '    <div class="center sliding">Dynamic Page ' + (++dynamicPageIndex) + '</div>' +
        '  </div>' +
        '</div>' +
        '<div class="pages">' +
        '  <!-- Page, data-page contains page name-->' +
        '  <div data-page="dynamic-pages" class="page">' +
        '    <!-- Scrollable page content-->' +
        '    <div class="page-content">' +
        '      <div class="content-block">' +
        '        <div class="content-block-inner">' +
        '          <p>Here is a dynamic page created on ' + new Date() + ' !</p>' +
        '          <p>Go <a href="#" class="back">back</a> or go to <a href="services.html">Services</a>.</p>' +
        '        </div>' +
        '      </div>' +
        '    </div>' +
        '  </div>' +
        '</div>'
    );
	return;
}





