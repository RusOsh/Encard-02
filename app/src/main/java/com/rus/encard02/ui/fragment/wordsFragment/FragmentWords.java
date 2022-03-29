package com.rus.encard02.ui.fragment.wordsFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;
import android.widget.Toast;

import com.rus.encard02.base.BaseFragment;
import com.rus.encard02.common.ISendKeyWord;
import com.rus.encard02.data.model.RoomModel.CategoryModel;
import com.rus.encard02.databinding.FragmentWordsBinding;
import com.rus.encard02.ui.fragment.addWord.AddWordsFragment;

public class FragmentWords extends BaseFragment<FragmentWordsBinding> implements ISendKeyWord {
    private AddWordsFragment addWordsFragment;
    private CategoryModel model;
    private WordsViewModel viewModel;
    private WordAdapter adapter;

    @Override
    protected FragmentWordsBinding bind() {
        return FragmentWordsBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addWordsFragment = new AddWordsFragment(this);
        openDialog();
    }

    private void openDialog() {
        binding.btnAddWord.setOnClickListener(v -> {
            addWordsFragment.show(requireActivity().getSupportFragmentManager(), model.getCategory());
        });
    }

    @Override
    protected void setupUI() {
        if (getArguments() != null) {
            model = (CategoryModel) getArguments().getSerializable("category");
        }
        adapter = new WordAdapter();
        binding.rvImage.setAdapter(adapter);
        viewModel = new ViewModelProvider(requireActivity()).get(WordsViewModel.class);
    }

    @Override
    protected void setupObservers() {
        viewModel.getLiveData().observe(getViewLifecycleOwner(), pixabayResponceResourse -> {
            switch (pixabayResponceResourse.status) {
                case SUCCESS:
                    viewModel.getRoomImg().observe(getViewLifecycleOwner(), wordsModels -> {
                        adapter.setList(wordsModels);
                    });
                    break;
                case LOADING:
                    Toast.makeText(requireActivity(), "Loading", Toast.LENGTH_SHORT).show();
                    break;
                case ERROR:
                    Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void send(String keyWord) {
        viewModel.getImage(keyWord);
    }
}