CREATE TABLE IF NOT EXISTS tour_package (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    price_cop REAL NOT NULL,
    duration_days INTEGER NOT NULL
);
