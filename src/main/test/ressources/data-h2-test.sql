-- This works every time since we have an in-memory-database.

-- Remove everything.
--DELETE FROM POST_COMMENTS;
--DELETE FROM COMMENT;
--DELETE FROM POST;
--DELETE FROM USER_;


-- Insert new users.
INSERT INTO USER_ (ID, EMAIL, PASSWORD) VALUES
(1,'luan','af68aaf7efd626d4487944e7fd745da3086cbda6d286f845976f2489ec164256ddddf4ca9b712d3e355ff062414e9a7a95cf2584483ce2631d312a030ca4a0ff'), --test
(2,'Rovi','d88e24c23234217b68b209559ffb1b654aadec940298c784d30cc44ba268ebd99b826794facd9ad4137abcac8896e9ee552dae2c6b2749b907dae6156fdf6f7e'); --test2