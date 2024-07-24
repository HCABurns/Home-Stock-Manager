from firebase_admin import credentials,db,initialize_app
from datetime import datetime
import dbHelper


# Fetch the service account key JSON file contents
cred = credentials.Certificate('key.json')
# Initialize the app with a service account, granting admin privileges
initialize_app(cred, {'databaseURL': dbHelper.url})

# Get day from the date
now = datetime.now()
date = now.strftime("%A").upper()

# Get data fromdb
ref = db.reference('menu')
data = ref.get()

# Update data locally
data[date]["name"] = ""

# Update Data in database
ref.update(data)
