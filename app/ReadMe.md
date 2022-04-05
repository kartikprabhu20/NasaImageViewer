NasaImageViewer is an app to explore the space images retrieved using https://api.nasa.gov/

Features:
* The Home page has a pager to alternate between Full Screen and Gallery.

* FullScreen
* Full Screen tab shows the image streched on the screen with the title of the image.
* It als has buttons to go next/previous and get more details
* The tab is also configured to automatically change view for every 10 seconds

* Gallery
* This tab list all the iamges in grid format.
* On clicking the image in the grid, we get the details of that nasa image.
* The recycler view is designed with databinding practices.
* BindingAdapters are used for onClick, and details are filled with NasaImageData entity directly.

*Details from fullscreen are displayed in a seperate activity while details of gallery are displayed in a fragment.