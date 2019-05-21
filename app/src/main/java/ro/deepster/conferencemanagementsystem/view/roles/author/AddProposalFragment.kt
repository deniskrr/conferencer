package ro.deepster.conferencemanagementsystem.view.roles.author


import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_add_proposal.*
import ro.deepster.conferencemanagementsystem.R

class AddProposalFragment : Fragment() {

    var selectedPaper: Uri? = null
    lateinit var viewModel: AuthorViewModel

    companion object {
        val RESULT_ADD_PROPOSAL = 69
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_proposal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(AuthorViewModel::class.java)

        button_paper_proposal.setOnClickListener {
            val chooseFile = Intent(Intent.ACTION_GET_CONTENT)
            chooseFile.addCategory(Intent.CATEGORY_OPENABLE)
            chooseFile.type = "application/pdf"
            val intent = Intent.createChooser(chooseFile, "Select the paper pdf")
            startActivityForResult(intent, RESULT_ADD_PROPOSAL)
        }

        button_add_proposal.setOnClickListener {
            val title = textlayout_title_proposal.editText!!.text.toString()
            val topics = textlayout_topics_proposal.editText!!.text.toString()
            val keywords = textlayout_keywords_proposal.editText!!.text.toString()
            viewModel.addProposal(title, topics, keywords, selectedPaper)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_ADD_PROPOSAL && resultCode == RESULT_OK && data != null) {
            // A paper was selected
            selectedPaper = data.data
        }
    }

}
