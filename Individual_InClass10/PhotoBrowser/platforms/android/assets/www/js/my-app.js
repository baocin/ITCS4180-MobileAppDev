//Michael Pedersen
//800810806
//Group 18
//InClass10
//4-4-16

// Initialize your app
var myApp = new Framework7({
    animateNavBackIcon:true,
     template7Pages: true,
});

// Export selectors engine
var $$ = Dom7;

// Add main View
var mainView = myApp.addView('.view-main', {
    // Enable dynamic Navbar
    dynamicNavbar: true,
    // Enable Dom Cache so we can use all inline pages
    domCache: true
});

var myPhotoBrowserStandalone = myApp.photoBrowser({
    zoom: 400,
     theme: 'dark',
    photos: ['https://farm9.staticflickr.com/8645/16031539611_b45d2bbe57_z.jpg','https://farm4.staticflickr.com/3933/15296234239_4f11d889a8_z.jpg', 'https://farm3.staticflickr.com/2949/15296430490_3366e6f050_z.jpg', 'https://farm4.staticflickr.com/3937/15483139575_c6eced4510_z.jpg']
});   

$$('#galleryButton').on('click', function () {
    myPhotoBrowserStandalone.open();
});

function success(imageData) {
	alert("Success!");
	mainView.router.load({
		pageName: 'photo',
		context: {
			imgDATA: imageData
		}
	});
}

function fail(message) {
	alert('Failed!');
}

$$('#takePhotoButton').on('click', function(){
	navigator.camera.getPicture(success, fail, { quality: 25,
		destinationType: Camera.DestinationType.DATA_URL
	});
});


