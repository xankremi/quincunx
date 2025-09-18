// ----------------------------
// Projections database
// ----------------------------
db = db.getSiblingDB("projections");

// Payments projections
db.createCollection("payments");
db.payments.createIndex({ createdAt: 1 });

// Notifications projections
db.createCollection("notifications");
db.notifications.createIndex({ createdAt: 1 });

// ----------------------------
// Files database (GridFS)
// ----------------------------
db = db.getSiblingDB("files");

// Collections for GridFS (will be created automatically when inserting files)
// fs.files and fs.chunks are standard for GridFS
