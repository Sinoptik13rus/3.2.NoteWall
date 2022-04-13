import errors.DeleteCommentException
import methods.*
import methods.Note
import java.util.Objects


data class NoteClass<A>(var first: A)

object NoteWall {
    private var notesArray = mutableListOf<Note>()
    private var commentArray = mutableListOf<Comment>()
    private var deleteNoteArray = mutableListOf<Note>()
    var deleteCommentArray = mutableListOf<Comment>()
    private var nid = 0
    private var id = 0


    fun add(note: Note): Int {
        id++
        val newNote = note.copy(id = id)
        notesArray.add(newNote)
        nid = newNote.id
        return nid
    }

    fun createComment(comment: Comment): Int {
        for ((index, i) in notesArray.withIndex()) {
            if (comment.noteId == i.id) {
                val newComment = comment.copy(cid = i.id)
                commentArray.add(newComment)
            }
        }
        return commentArray.lastIndex

    }

    fun delete(delete: Note): Boolean {
        notesArray.remove(delete)
        return true
    }

    fun deleteComment(deleteComment: Comment): Boolean {
        for ((index, i) in deleteCommentArray.withIndex()) {
            if (i.cid == deleteComment.cid) {
                throw DeleteCommentException("This comment has already been deleted")
            }
        }
        for ((index, i) in commentArray.withIndex()) {
            if (deleteComment.cid == i.cid) {
                deleteCommentArray.add(deleteComment)
                commentArray.remove(deleteComment)
                return true
            }
        }
        return false
    }

    fun edit(edit: Note): Boolean {
        for ((index, i) in notesArray.withIndex()) {
            if (edit.id == i.id) {
                notesArray[index] = edit
            }
            return true
        }
        return false
    }

    fun editComment(editComment: Comment): Boolean {
        for ((index, i) in commentArray.withIndex()) {
            if (editComment.cid == i.cid) {
                commentArray[index] = editComment
            }
            return true
        }
        return false
    }

    fun get(get: Get): List<Note> {
        val i = 1
        val newNotesArray = mutableListOf<Note>()
        while (i <= get.count) {
            for (j in get.noteIds) {
                for ((index, k) in notesArray.withIndex()) {
                    if (j == k.id) {
                        newNotesArray.add(notesArray[index])
                    }
                }
            }
        }
        return newNotesArray

    }

    fun getById(getByID: GetByID): Note {
        var note = Note(0, "none", "none")
        for ((index, i) in notesArray.withIndex()) {
            if (i.id == getByID.noteId) {
                note = notesArray[index]
            }
        }
        return note
    }

        fun getComments(getComments: GetComments): List<Comment> {
            val i = 0
            var newGetComments = mutableListOf<Comment>()
            while (i <= getComments.count) {
                for ((index, j) in commentArray.withIndex()) {
                    if (getComments.noteId == j.noteId) {
                        val getNote = commentArray[index]
                        newGetComments.add(getNote)
                    }
                }
            }
            return newGetComments
        }

        fun restoreComments(restoreComments: RestoreComments): Boolean {
            for ((index, i) in deleteCommentArray.withIndex()) {
                if (restoreComments.commentId == i.cid) {
                    val newRestore = deleteCommentArray[index].copy(
                        cid = restoreComments.commentId,
                        noteId = deleteCommentArray[index].noteId,
                        message = deleteCommentArray[index].message
                    )
                    commentArray.add(newRestore)
                    return true
                }
            }
            return false
        }

    fun clear() {
        notesArray.clear()
    }

}

