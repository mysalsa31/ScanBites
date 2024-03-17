package com.example.scanbite_v1_camera_function.ui.review

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scanbite_v1_camera_function.R
import com.example.scanbite_v1_camera_function.databinding.FragmentReviewBinding
import com.example.scanbite_v1_camera_function.util.ReviewAdapter
import com.example.scanbite_v1_camera_function.util.ReviewData
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class ReviewFragment : Fragment(), AddReviewPopupFragment.ibtnNextClickListener,
    ReviewAdapter.IReviewAdapterClicks {

    private lateinit var auth: FirebaseAuth
    private lateinit var refDatabase: DatabaseReference
    private lateinit var binding: FragmentReviewBinding
    private var popUpReviewFragment: AddReviewPopupFragment? = null
    private lateinit var adapter: ReviewAdapter
    private lateinit var listMutable: MutableList<ReviewData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        dataFirebase()
        registerReviews()
    }

    private fun registerReviews(){
        binding.btnAddReview.setOnClickListener {
            if (popUpReviewFragment != null)
                childFragmentManager.beginTransaction().remove(popUpReviewFragment!!).commit()
            popUpReviewFragment = AddReviewPopupFragment()
            popUpReviewFragment!!.setListener(this)
            popUpReviewFragment!!.show(childFragmentManager, AddReviewPopupFragment.TAG)

        }
    }

    private fun init(view: View){
        auth = Firebase.auth
        refDatabase = FirebaseDatabase.getInstance().reference.child("Reviews").child(auth.currentUser?.uid.toString())

        binding.recycleReview.setHasFixedSize(true)
        binding.recycleReview.layoutManager = LinearLayoutManager(context)
        listMutable = mutableListOf()
        adapter = ReviewAdapter(listMutable)
        adapter.setListener(this)
        binding.recycleReview.adapter = adapter
    }

    private fun dataFirebase(){
        refDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                listMutable.clear()
                for (reviewSnapshot in snapshot.children){
                    val reviewTask = reviewSnapshot.key?.let{
                        ReviewData(it, reviewSnapshot.value.toString())
                    }
                    if (reviewTask != null){
                        listMutable.add(reviewTask)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun onSaveReview(review: String, etReview: TextInputEditText) {
        refDatabase.push().setValue(review).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(context, "Review saved successfully.", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
            etReview.text = null
            popUpReviewFragment!!.dismiss()
        }

    }

    override fun onUpdateReview(reviewData: ReviewData, etReview: TextInputEditText) {
        val map = HashMap<String, Any>()
        map[reviewData.reviewId] = reviewData.review
        refDatabase.updateChildren(map).addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(context, "Updated Review Successfully.", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
            etReview.text = null
            popUpReviewFragment!!.dismiss()
        }
    }

    override fun btnDeleteReviewClicked(reviewData: ReviewData) {
        refDatabase.child(reviewData.reviewId).removeValue().addOnCompleteListener{
            if (it.isSuccessful){
                Toast.makeText(context, "Deleted Review Successfully.", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun btnEditReviewClicked(reviewData: ReviewData) {
        if (popUpReviewFragment != null)
            childFragmentManager.beginTransaction().remove(popUpReviewFragment!!).commit()

        popUpReviewFragment = AddReviewPopupFragment.newInstance(reviewData.reviewId, reviewData.review)
        popUpReviewFragment!!.setListener(this)
        popUpReviewFragment!!.show(childFragmentManager, AddReviewPopupFragment.TAG)

    }
}