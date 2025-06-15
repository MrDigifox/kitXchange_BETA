README.md
KitXchange – Final Submission (Week 5)

I’m Israel Ortiz Rodriguez (ECPI student) and this is my Android app for buying, selling, and trading match-worn soccer kits.  
Version **0.3-beta** – code **2** (tag **Final**)

-----------------------------------------------

1. What’s new since the Week-4 checkpoint

Area | Week-4 status | Week-5 upgrade |

*Database- only a Listing data class, hooked up Room with DAO, seeded 12 demo listings on first run
*Profile screen- placeholder with avatar + switch, finished: avatar upload, favorite-team textbox, “show location” toggle, Save button 
*Security, plain prefs, no hashing, passwords now, bcrypt hashed<br>, user settings stored in EncryptedSharedPreferences, avatars saved in internal app storage (private) 
*XMR price- Retrofit call working, moved to a coroutine + cached the price for 10 min (traffic saver)
*Look & feel- default purple, new pastel theme, rounded buttons, adaptive launcher icon 

----------------------------------------------

2. Security highlights

*Login hashing– Passwords are never stored in plain text `BCrypt.hashpw()` + salt.  
*Private images– Avatar files live in `filesDir` (mode `MODE_PRIVATE`), so no other app can peek.  
*Opt-in location– Location is off by default and its state is AES-256-encrypted via `EncryptedSharedPreferences`.

--------------------------------------------------


