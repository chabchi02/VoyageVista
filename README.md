# VoyageVista

VoyageVista is a versatile travel app designed to simplify your exploration of North America. It offers a range of practical features for on-the-go travelers. With VoyageVista, you can effortlessly access recommendations for nearby hotels, restaurants, and events, all powered by API calls to multiple sources. The app's standout feature is its AI-driven itinerary generation, which tailors your travel plans based on the number of days you'll be in a city. Additionally, VoyageVista keeps you informed about the availability of City Passes and Go City packages, ensuring you can access popular attractions at discounted rates. The user-friendly interface neatly presents all the essential information you need to make informed choices during your trip. Whether you're a local explorer or a visitor, VoyageVista is your reliable travel companion, designed to make your journeys more enjoyable and efficient.

| Content   | API source |
| :-: | :-: |
| Restaurants  | Google Places API    |
| Hotels | Booking.com    |
| Events    | Ticketmaster    |
| Chatbot    | ChatGPT    |
| Weather | OpenWeatherMap |

### Timeline

#### September 11-24 - Requirements elicitation

During this period, the application features were elicited and the main functions of the application were decided on. The app, at its base, will allow users to navigate hotels, restaurants, and events around them. These features will be made possible with the use of public APIs such as Google Places, Ticketmaster, and Booking.com APIs. After these features are implemented, the goal will be to integrate AI, possibly ChatGPT into the application in the form of an itinerary generator.

Some challenges expected: Integrating data from various public APIs, such as Google Places, Ticketmaster, and Booking.com, can be challenging. These APIs may have different data structures, authentication methods, and usage limitations. Ensuring seamless data retrieval and consistency in data presentation can be a complex task. Comprehensive testing is required to validate the functionality, performance, and security of the application. This includes testing the integration of various APIs and the AI component.

#### September 25-October 15 - Beginning of development

During this phase, accounts were created on the various public API sources and keys were gathered. Some of the features were implemented and tested. Firstly, the user's location (coordinates) was collected using LocationManager, a function part of the Android package. These coordinates were passed to the OpenWeatherMap API which returned the user's city name. Using this information, I was able to generate a list of restaurants around the user and create the first view of the application. The restaurant information was retrieved using the Google Places API. The restaurants are displayed on a custom list view which displays the restaurant's photo, its name, location, and price level. This first view will be replicated to display the hotels and events around the user.

Some challenges faced: When interacting with external APIs like OpenWeatherMap and Google Places, errors can occur. Handling these errors gracefully to prevent app crashes or unexpected behavior is important for a smooth user experience. Many tests were performed to ensure errors were handled or did not occur. Managing asynchronous calls and ensuring that data is displayed in a timely manner without freezing the app can be challenging. Working with features such as LocationManger is not an intuitive process. It took a lot of documentation reading and testing to perfect. Another problem I faced was making the API calls themselves and gathering the responses from the JSON response format. After reviewing the "retrofit" package documentation, which makes the API calls, I learned how to make my first API call and this technique was replicated for all successive API calls to other sources. Now that I have gained more experience working with the many different functions available for Android development, I am confident in my ability to develop further functions in future weeks.

#### October 16-29 - Major development

In this period, I made some major improvements to the application. After a lot of trial and error in previous weeks, I became familiar with most of the functions I need to develop my application. After having completed the list of restaurants in the previous period, I started working on and finished the list of events and hotels (pulled from Ticketmaster and Booking.com, respectively). This part was simple as its implementation was very similar. After having finished creating these view, the basic features of the application were achieved. I could now focus on working on the AI component. After brainstorming some ideas, I thought that including a chatbot where a user could ask their travel questions would be a good idea. Therefore, using the ChatGPT AI, I was able to create a chatbot that responds to the user's questions regarding travel. The user would be able to ask questions such as "What should I bring with me on a trip to Ottawa during winter?" or "What sushi restaurant do you recommend in Toronto?" and receive a useful response. I fully implemented this feature and tested it out with friends. Following this, I made some modifictions to the main page of the application. It now displays a photo of the city the user is in, the current weather (using OpenWeatherMap API), as well as a ChatGPT one-line suggestion to the user, such as "How about a leisurely bike ride along the scenic Ottawa River pathway?" Then, I created a user settings page which allows the user to provide their name. It displays the user's city and their coordinates. My next step will be to implement the itinerary generation feature which will allow the user to provide the number of days they will be in the city and then provide them with an itinerary.

Challenges faced: 



