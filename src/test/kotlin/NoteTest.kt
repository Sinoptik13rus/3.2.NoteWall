import NoteWall.deleteCommentArray
import errors.DeleteCommentException
import methods.*
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NoteTest {

    @Before
    fun clear() {
        NoteWall.clear()
    }

    @Test
    fun addTest() {
        NoteWall.clear()
        val expected = 6
        val addNote = Note(1, "Mess in my head", "My Head")
        val actual = NoteWall.add(addNote)

        assertEquals(expected, actual)

    }

    @Test
    fun createCommentTest() {
        val expected = 0
        val finish = NoteWall
        finish.add(Note(1, "Mess in my head", "My Head"))
        finish.add(Note(3, "Mess in my head", "My Head"))
        val comment = Comment(cid = 1, noteId = 1, message = "Oh-Oh-Oh")
        val comment1 = Comment(cid = 2, noteId = 2, message = "Wow - Wow")
        val result1 = finish.createComment(comment)
        assertEquals(expected, result1)

    }

    @Test
    fun delete() {
        val finish = NoteWall
        val delete = Note(1, "Mess in my head", "My Head")
        finish.add(Note(1, "Mess in my head", "My Head"))
        val result = finish.delete(delete)
        assertTrue(result)

    }

    @Test
    fun deleteCommentTrue() {
        val finish = NoteWall
        finish.add(Note(1, "Mess in my head", "My Head"))
        finish.add(Note(2, "Mess in your head", "Your Head"))
        finish.createComment(Comment(cid = 1, noteId = 1, message = "Oh-Oh-Oh"))
        finish.createComment(Comment(cid = 2, noteId = 2, message = "Oh-Oh-Oh"))
        val comment1 = Comment(cid = 1, noteId = 1, message = "Oh-Oh-Oh")
        val result = finish.deleteComment(comment1)
        assertTrue(result)
    }

    @Test
    fun deleteCommentFalse() {
        val finish = NoteWall
        finish.add(Note(1, "Mess in my head", "My Head"))
        finish.add(Note(2, "Mess in your head", "Your Head"))
        finish.createComment(Comment(cid = 1, noteId = 1, message = "Oh-Oh-Oh"))
        finish.createComment(Comment(cid = 2, noteId = 2, message = "Oh-Oh-Oh"))
        val comment1 = Comment(cid = 5, noteId = 3, message = "UPD")
        val result = finish.deleteComment(comment1)
        assertFalse(result)
    }

    @Test
    fun editTestTrue() {
        val finish = NoteWall
        finish.add(Note(1, "Mess in my head", "My Head"))
        finish.add(Note(2, "Mess in your head", "Your Head"))
        val edit = Note(0, "Job in my head", "My Foot")
        val result = finish.edit(edit)
        assertTrue(result)
    }

    @Test
    fun editCommentTrue() {
        val finish = NoteWall
        finish.createComment(Comment(cid = 1, noteId = 1, message = "Oh-Oh-Oh"))
        finish.createComment(Comment(cid = 2, noteId = 2, message = "Oh-Oh-Oh"))
        val comment1 = Comment(cid = 1, noteId = 1, message = "UPD")
        val result = finish.editComment(comment1)
        assertTrue(result)
    }

    @Test
    fun get() {
    }

    @Test
    fun getById() {
    }

    @Test
    fun getComments() {
    }

    @Test
    fun restoreComments() {
    }
}